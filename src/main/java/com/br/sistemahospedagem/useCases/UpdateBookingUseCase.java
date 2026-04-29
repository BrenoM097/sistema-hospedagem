package com.br.sistemahospedagem.useCases;

import com.br.sistemahospedagem.commands.UpdateBookingCommand;
import com.br.sistemahospedagem.controller.CustomResponse;
import com.br.sistemahospedagem.domain.gateways.BookingGateway;
import com.br.sistemahospedagem.domain.gateways.RoomGateway;
import com.br.sistemahospedagem.domain.pojos.Booking;
import com.br.sistemahospedagem.domain.pojos.Room;
import com.br.sistemahospedagem.domain.services.RoomAvailabilityCheck;

import java.util.Optional;

public class UpdateBookingUseCase {
    private final BookingGateway bookingGateway;
    private final RoomGateway roomGateway;

    public UpdateBookingUseCase(BookingGateway bookingGateway, RoomGateway roomGateway) {
        this.bookingGateway = bookingGateway;
        this.roomGateway = roomGateway;
    }

    public CustomResponse execute(UpdateBookingCommand command) {
        boolean availableRoom = RoomAvailabilityCheck.checkRoom(bookingGateway.findLatestBookingByRoomId(command.roomId()));
        Optional<Booking> optionalBooking = bookingGateway.findById(command.bookingId());
        if(optionalBooking.isEmpty()) {
            return new CustomResponse("booking not found");
        }
        Booking booking = optionalBooking.get();

        if(!availableRoom) {
            return new CustomResponse("Quarto selecionado indisponivel");
        } else if(command.roomId() != null) {
            int totalDays = booking.getTotalDays();
            Room existingRoom = roomGateway.findRoomById(command.roomId());
            Double totalValue = totalDays * existingRoom.getDailyValue();
        }

        bookingRepository.updateBooking(booking.getId(), booking.getFirstName(), booking.getLastName(), booking.getEmail(), booking.getCpf(), booking.getCheckIn(), booking.getCheckOut(), booking.isParkingLot(), booking.getCarType().toString(), booking.getCheckOutTime().toString(), booking.getRoomId());
        return new CustomResponse("Alteração feita com sucesso!");
    }
}
