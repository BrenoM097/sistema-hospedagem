package com.br.sistemahospedagem.controller;


import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.br.sistemahospedagem.domain.booking.Booking;
import com.br.sistemahospedagem.dtos.BookingDTO;
import com.br.sistemahospedagem.repositories.BookingRepository;
import com.br.sistemahospedagem.service.BookingService;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    BookingService clienteService;

    @Autowired
    BookingRepository bookingRepository;
    
    @PostMapping
    public ResponseEntity<Booking> reserve(@RequestBody BookingDTO booking) {
        Booking newBooking = clienteService.reserve(booking);  
        return new ResponseEntity<>(newBooking, HttpStatus.CREATED);
    }

    @GetMapping("/date")
    public ResponseEntity<List<Booking>> reserveInBetween(LocalDateTime dataInicio, LocalDateTime dataFim) {
        List<Booking> bookingBetweenDates = bookingRepository.findBookingsByDates(dataInicio, dataFim);
        return new ResponseEntity<>(bookingBetweenDates, HttpStatus.CREATED);
    }

}
