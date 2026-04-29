package com.br.sistemahospedagem.useCases;

import com.br.sistemahospedagem.commands.ReserveBookingCommand;
import com.br.sistemahospedagem.domain.gateways.BookingGateway;
import com.br.sistemahospedagem.domain.gateways.EmailGateway;
import com.br.sistemahospedagem.domain.gateways.RoomGateway;
import com.br.sistemahospedagem.domain.gateways.UserGateway;
import com.br.sistemahospedagem.domain.pojos.Booking;
import com.br.sistemahospedagem.domain.pojos.Room;
import com.br.sistemahospedagem.domain.pojos.User;
import org.springframework.transaction.annotation.Transactional;

public class ReserveRoomUseCase {
    private final BookingGateway bookingGateway;
    private final RoomGateway roomGateway;
    private final UserGateway userGateway;
    private final EmailGateway emailGateway;

    public ReserveRoomUseCase(BookingGateway bookingGateway, RoomGateway roomGateway, UserGateway userGateway, EmailGateway emailGateway) {
        this.bookingGateway = bookingGateway;
        this.roomGateway = roomGateway;
        this.userGateway = userGateway;
        this.emailGateway = emailGateway;
    }

    @Transactional
    public Booking execute(ReserveBookingCommand command) {
        Room room = roomGateway.findRoomById(command.roomId());
        User user = userGateway.findUserById(command.userId());

        Booking booking = new Booking(command.checkIn(), command.checkOut(), user, command.parkingLot(), command.carType(), command.checkOutTime());
        booking.calculateTotalValue(room);

        boolean emailStatus = emailGateway.sendEmail(booking);
        if(emailStatus) {
            booking.confirmEmailSent();
        } else {
            booking.flagEmailError();
        }

        return bookingGateway.save(booking);
    }
}
