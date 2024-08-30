package com.br.sistemahospedagem.service;

import com.br.sistemahospedagem.controller.CustomResponse;
import com.br.sistemahospedagem.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.sistemahospedagem.domain.email.StatusEmail;
import com.br.sistemahospedagem.domain.booking.Booking;
import com.br.sistemahospedagem.domain.email.EmailModel;
import com.br.sistemahospedagem.domain.room.Room;
import com.br.sistemahospedagem.dtos.BookingDTO;
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

    public Booking reserve(BookingDTO booking) {
        Room existingRoom = roomService.findRoomById(booking.getRoomId());
        User user = userService.findUserById(booking.getUserId());
        if(existingRoom == null) {
            throw new RoomNotFoundException("Quarto não encontrado");
        }

        Booking newReserva = new Booking(booking);
        newReserva.setRoom(existingRoom);
        newReserva.setUser(user);
        newReserva.setTotalValue(getTotalDays(newReserva) * existingRoom.getDailyValue());

        if(!enviarEmailReservaConfirmada(newReserva)) {
            System.out.println("Falha ao enviar o email");
            newReserva.setStatusEmail(StatusEmail.ERROR);
            this.saveReserve(newReserva);
            return newReserva;
        }
        System.out.println("Email enviado com sucesso!");
        newReserva.setStatusEmail(StatusEmail.SENT);
        this.saveReserve(newReserva);
        
        return newReserva;
    }

    @Transactional
    public CustomResponse updateReserve(BookingDTO booking) {
        boolean availableRoom = roomService.isThisRoomAvailable(this.findLatestBookingByRoomId(booking.getRoomId()));
        if(!availableRoom) {
            return new CustomResponse("Quarto selecionado indisponivel");
        } else if(booking.getRoomId() != 0) {
            int totalDays = getTotalDays(booking);
            Room existingRoom = roomService.findRoomById(booking.getRoomId());
            Double totalValue = totalDays * existingRoom.getDailyValue();
        }

        bookingRepository.updateBooking(booking.getId(), booking.getFirstName(), booking.getLastName(), booking.getEmail(), booking.getCpf(), booking.getCheckIn(), booking.getCheckOut(), booking.isParkingLot(), booking.getCarType().toString(), booking.getCheckOutTime().toString(), booking.getRoomId());
        return new CustomResponse("Alteração feita com sucesso!");
    }

    private void saveReserve(Booking booking) {
        this.bookingRepository.save(booking);
    }

    private int getTotalDays(Booking booking) {
        return booking.getCheckOut().getDayOfMonth() - booking.getCheckIn().getDayOfMonth();
    }

    private int getTotalDays(BookingDTO booking) {
        return booking.getCheckOut().getDayOfMonth() - booking.getCheckIn().getDayOfMonth();
    }

    public Booking findLatestBookingByRoomId(int roomId) {
        return bookingRepository.findLatestBookingByRoomId(roomId);
    }

    public Optional<Booking> findBookingById(Long id){ return bookingRepository.findBookingById(id); }

    public List<Booking> findAll() { return  bookingRepository.findAll(); }

   private boolean enviarEmailReservaConfirmada(Booking booking) {
    EmailModel emailModel = new EmailModel();
    emailModel.setEmailTo(booking.getUser().getEmail());
    emailModel.setSubject("Reserva feita com sucesso! Hospedagem agradece sua preferência");
    emailModel.setText(emailService.processEmailTemplate(booking.getUser().getFirstName(), booking.getCheckIn(), booking.getCheckOut()));

    StatusEmail statusEmail = emailService.sendEmail(emailModel);
       return statusEmail.compareTo(StatusEmail.ERROR) != 0;
   }
    public List<Booking> findBookingsByDates(LocalDate checkIn, LocalDate checkOut) {
        return bookingRepository.findBookingsByDates(checkIn, checkOut);
    }
}
