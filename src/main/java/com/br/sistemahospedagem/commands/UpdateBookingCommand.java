package com.br.sistemahospedagem.commands;

import com.br.sistemahospedagem.infra.schemas.booking.CarType;
import com.br.sistemahospedagem.infra.schemas.booking.CheckOutTime;

import java.time.LocalDate;

public record UpdateBookingCommand(
        Long bookingId,
        LocalDate checkIn,
        LocalDate checkOut,
        Integer roomId,
        Boolean parkingLot,
        CarType carType,
        CheckOutTime checkOutTime
) {}
