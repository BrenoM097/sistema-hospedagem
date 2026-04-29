package com.br.sistemahospedagem.commands;

import com.br.sistemahospedagem.infra.schemas.booking.CarType;
import com.br.sistemahospedagem.infra.schemas.booking.CheckOutTime;

import java.time.LocalDate;

public record ReserveBookingCommand(
        LocalDate checkIn,
        LocalDate checkOut,
        int roomId,
        int userId,
        boolean parkingLot,
        CarType carType,
        CheckOutTime checkOutTime
) {}
