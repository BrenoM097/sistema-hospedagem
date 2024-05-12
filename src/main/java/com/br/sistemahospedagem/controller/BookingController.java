package com.br.sistemahospedagem.controller;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
import com.br.sistemahospedagem.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/hospedagem")
@Api("Controlador para requisições sobre reservas")
public class BookingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookingController.class);
    private final BookingService bookingService;
    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    //Método para criar uma nova reserva de quarto
    @ApiOperation("Operação para criar uma reserva")
    @PostMapping("/createBooking")
    public ResponseEntity<Booking> reserve(@RequestBody BookingDTO booking) {
        LOGGER.info("Received booking request: {}", booking);

        Booking newBooking = bookingService.reserve(booking);  
        return new ResponseEntity<>(newBooking, HttpStatus.CREATED);
    }

    //Método para checar reservas entre um determinado intervalo de datas
    @ApiOperation("Operação para consultar uma reserva entre um intervalo de datas")
    @GetMapping("/date")
    public ResponseEntity<List<Booking>> reservesInBetweenDates(@ApiParam("Parâmetro que consiste em ser a data inicial") @RequestParam("checkIn") LocalDate checkIn,@ApiParam("Parâmetro que consiste em ser a data final") @RequestParam("checkOut") LocalDate checkOut) {

        List<Booking> bookingBetweenDates = bookingService.findBookingsByDates(checkIn, checkOut);
        return new ResponseEntity<>(bookingBetweenDates, HttpStatus.OK);
    }

    //Método que retorna todas as reservas
    @ApiOperation("Operação que lista todas as reservas")
    @GetMapping("bookings")
    public ResponseEntity<List<Booking>> allBookings() {
        List<Booking> allBookings = bookingService.findAll();
        return new ResponseEntity<>(allBookings, HttpStatus.OK);
    }


    //Retorna uma reserva com base em um Id fornecido
    @ApiOperation("Operação que consulta uma reserva em especifico com base em um ID")
    @GetMapping("booking")
    public ResponseEntity<Optional<Booking>> bookingById(@ApiParam("Parâmetro que consiste em ser o ID da reserva a ser consultada") @RequestParam("id") Long id) {
        Optional<Booking> booking = bookingService.findBookingById(id);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    //Atualiza uma reserva
    @ApiOperation("Operação que atualiza uma reserva")
    @PostMapping("updateBooking")
    public ResponseEntity<CustomResponse> updateBooking(@ApiParam("Parâmetro que consiste em ser a reserva atualizada") @RequestBody BookingDTO booking) {
        LOGGER.info("Received booking request: {}", booking);
        CustomResponse response = bookingService.updateReserve(booking);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
