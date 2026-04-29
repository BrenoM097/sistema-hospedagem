package com.br.sistemahospedagem.service;

import com.br.sistemahospedagem.controller.CustomResponse;
import com.br.sistemahospedagem.infra.schemas.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.sistemahospedagem.infra.schemas.email.StatusEmail;
import com.br.sistemahospedagem.infra.schemas.booking.BookingModel;
import com.br.sistemahospedagem.infra.schemas.email.EmailModel;
import com.br.sistemahospedagem.infra.schemas.room.RoomSchema;
import com.br.sistemahospedagem.dtos.request.BookingRequestDTO;
import com.br.sistemahospedagem.exceptions.RoomNotFoundException;
import com.br.sistemahospedagem.repositories.BookingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    RoomService roomService;
    EmailService emailService;
    BookingRepository bookingRepository;
    UserService userService;
    public BookingService(@Autowired BookingRepository bookingRepository, @Autowired EmailService emailService, @Autowired RoomService roomService, @Autowired UserService userService) {
        this.bookingRepository = bookingRepository;
        this.emailService = emailService;
        this.roomService = roomService;
        this.userService = userService;
    }


    public BookingModel findLatestBookingByRoomId(int roomId) {
        return bookingRepository.findLatestBookingByRoomId(roomId);
    }

    public Optional<BookingModel> findBookingById(Long id){ return bookingRepository.findBookingById(id); }

    public List<BookingModel> findAll() { return  bookingRepository.findAll(); }

   private boolean enviarEmailReservaConfirmada(BookingModel bookingModel) {
    EmailModel emailModel = new EmailModel();
    emailModel.setEmailTo(bookingModel.getUser().getEmail());
    emailModel.setSubject("Reserva feita com sucesso! Hospedagem agradece sua preferência");
    emailModel.setText(emailService.processEmailTemplate(bookingModel.getUser().getFirstName(), bookingModel.getCheckIn(), bookingModel.getCheckOut()));

    StatusEmail statusEmail = emailService.sendEmail(emailModel);
       return statusEmail.compareTo(StatusEmail.ERROR) != 0;
   }
    public List<BookingModel> findBookingsByDates(LocalDate checkIn, LocalDate checkOut) {
        return bookingRepository.findBookingsByDates(checkIn, checkOut);
    }
}
