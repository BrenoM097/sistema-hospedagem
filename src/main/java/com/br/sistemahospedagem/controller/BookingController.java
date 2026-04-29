package com.br.sistemahospedagem.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.br.sistemahospedagem.commands.ReserveBookingCommand;
import com.br.sistemahospedagem.domain.pojos.Booking;
import com.br.sistemahospedagem.dtos.response.BookingResponseDTO;
import com.br.sistemahospedagem.useCases.ReserveRoomUseCase;
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
import com.br.sistemahospedagem.infra.schemas.booking.BookingModel;
import com.br.sistemahospedagem.dtos.request.BookingRequestDTO;
import com.br.sistemahospedagem.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/hospedagem")
@Api("Controlador para requisições sobre reservas")
public class BookingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookingController.class);
    private final BookingService bookingService;
    private final ReserveRoomUseCase reserveRoomUseCase;
    @Autowired
    public BookingController(BookingService bookingService, ReserveRoomUseCase reserveRoomUseCase) {
        this.bookingService = bookingService;
        this.reserveRoomUseCase = reserveRoomUseCase;
    }

    //Método para criar uma nova reserva de quarto
    @ApiOperation("Operação para criar uma reserva")
    @PostMapping("/createBooking")
    public ResponseEntity<BookingResponseDTO> reserve(@RequestBody BookingRequestDTO booking) {
        LOGGER.info("Received booking request: {}", booking);
        ReserveBookingCommand command = new ReserveBookingCommand(booking.checkIn(), booking.checkOut(), booking.roomId(), booking.userId() ,booking.parkingLot(), booking.carType(), booking.checkOutTime());
        Booking bookingResponse = reserveRoomUseCase.execute(command);

        return new ResponseEntity<>(new BookingResponseDTO(bookingResponse), HttpStatus.CREATED);
    }

    //Método para checar reservas entre um determinado intervalo de datas
    @ApiOperation("Operação para consultar uma reserva entre um intervalo de datas")
    @GetMapping("/date")
    public ResponseEntity<List<BookingModel>> reservesInBetweenDates(@ApiParam("Parâmetro que consiste em ser a data inicial") @RequestParam("checkIn") LocalDate checkIn, @ApiParam("Parâmetro que consiste em ser a data final") @RequestParam("checkOut") LocalDate checkOut) {

        List<BookingModel> bookingModelBetweenDates = bookingService.findBookingsByDates(checkIn, checkOut);
        return new ResponseEntity<>(bookingModelBetweenDates, HttpStatus.OK);
    }

    //Método que retorna todas as reservas
    @ApiOperation("Operação que lista todas as reservas")
    @GetMapping("bookings")
    public ResponseEntity<List<BookingModel>> allBookings() {
        List<BookingModel> allBookingEntities = bookingService.findAll();
        return new ResponseEntity<>(allBookingEntities, HttpStatus.OK);
    }


    //Retorna uma reserva com base em um Id fornecido
    @ApiOperation("Operação que consulta uma reserva em especifico com base em um ID")
    @GetMapping("booking")
    public ResponseEntity<Optional<BookingModel>> bookingById(@ApiParam("Parâmetro que consiste em ser o ID da reserva a ser consultada") @RequestParam("id") Long id) {
        Optional<BookingModel> booking = bookingService.findBookingById(id);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    //Atualiza uma reserva
    @ApiOperation("Operação que atualiza uma reserva")
    @PostMapping("updateBooking")
    public ResponseEntity<CustomResponse> updateBooking(@ApiParam("Parâmetro que consiste em ser a reserva atualizada") @RequestBody BookingRequestDTO booking) {
        LOGGER.info("Received booking request: {}", booking);
        CustomResponse response = bookingService.updateReserve(booking);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
