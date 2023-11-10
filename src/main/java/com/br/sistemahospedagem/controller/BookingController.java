package com.br.sistemahospedagem.controller;


import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.br.sistemahospedagem.domain.booking.Booking;
import com.br.sistemahospedagem.dtos.BookingDTO;
import com.br.sistemahospedagem.repositories.BookingRepository;
import com.br.sistemahospedagem.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/hotel")
public class BookingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    BookingService clienteService;

    @Autowired
    BookingRepository bookingRepository;
    
    @PostMapping("/createBooking")
    public ResponseEntity<Booking> reserve(@RequestBody BookingDTO booking) {
        LOGGER.info("Received booking request: {}", booking);

        Booking newBooking = clienteService.reserve(booking);  
        return new ResponseEntity<>(newBooking, HttpStatus.CREATED);
    }

    @GetMapping("/date")
    public ResponseEntity<List<Booking>> reserveInBetween(@RequestParam("checkIn") LocalDate checkIn, @RequestParam("checkOut") LocalDate checkOut) {
        LOGGER.info("Received dates request: {}", checkIn, checkOut);

        List<Booking> bookingBetweenDates = bookingRepository.findBookingsByDates(checkIn, checkOut);
        return new ResponseEntity<>(bookingBetweenDates, HttpStatus.OK);
    }

}
