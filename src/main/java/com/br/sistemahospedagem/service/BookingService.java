package com.br.sistemahospedagem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.sistemahospedagem.config.StatusEmail;
import com.br.sistemahospedagem.domain.booking.Booking;
import com.br.sistemahospedagem.domain.email.EmailModel;
import com.br.sistemahospedagem.domain.room.Room;
import com.br.sistemahospedagem.dtos.BookingDTO;
import com.br.sistemahospedagem.exceptions.RoomNotFoundException;
import com.br.sistemahospedagem.repositories.BookingRepository;

@Service
public class BookingService {
    @Autowired
    RoomService roomService;

    @Autowired
    EmailService emailService;

    @Autowired
    BookingRepository bookingRepository;

    public Booking reserve(BookingDTO booking) {
        Room existingRoom = roomService.findRoomById(booking.getRoomId());
        if(existingRoom == null) {
            throw new RoomNotFoundException("Quarto não encontrado");
        }

        Booking newReserva = new Booking(booking);
        newReserva.setRoom(existingRoom);
        newReserva.setTotalValue(getTotalDays(newReserva) * existingRoom.getDailyValue());
        enviarEmailReservaConfirmada(newReserva);
        System.out.println("Email enviado com sucesso!");
        newReserva.setStatusEmail(StatusEmail.SENT);
        this.saveReserve(newReserva);
        
        return newReserva;
    }


    private void saveReserve(Booking booking) {
        this.bookingRepository.save(booking);
    }

    private int getTotalDays(Booking booking) {
        int dias = booking.getCheckOut().getDayOfMonth() - booking.getCheckIn().getDayOfMonth();
        return dias;
    }


    public Booking findLatestBookingByRoomId(int roomId) {
        return bookingRepository.findLatestBookingByRoomId(roomId);
    }

   private void enviarEmailReservaConfirmada(Booking booking) {
    EmailModel emailModel = new EmailModel();
    emailModel.setEmailTo(booking.getEmail());
    emailModel.setSubject("Reserva feita com sucesso! Hospedagem agradece sua preferência");
    emailModel.setText(emailService.processEmailTemplate(booking.getFirstName(), booking.getCheckIn(), booking.getCheckOut()));

    emailService.sendEmail(emailModel);
}


}
