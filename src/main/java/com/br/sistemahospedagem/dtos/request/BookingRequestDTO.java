package com.br.sistemahospedagem.dtos.request;

import java.time.LocalDate;
import com.br.sistemahospedagem.infra.schemas.booking.CarType;
import com.br.sistemahospedagem.infra.schemas.booking.CheckOutTime;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record BookingRequestDTO (
        Long id,
        int userId,
        LocalDate checkIn,
        LocalDate checkOut,
        boolean parkingLot,
        CarType carType,
        int roomId,
        Double totalValue,
        @Enumerated(EnumType.STRING)
        CheckOutTime checkOutTime
){}
