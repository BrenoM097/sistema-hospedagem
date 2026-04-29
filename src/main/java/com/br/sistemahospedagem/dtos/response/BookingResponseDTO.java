package com.br.sistemahospedagem.dtos.response;

import com.br.sistemahospedagem.domain.pojos.Booking;
import com.br.sistemahospedagem.infra.schemas.booking.CarType;
import com.br.sistemahospedagem.infra.schemas.booking.CheckOutTime;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

public record BookingResponseDTO(
        Long id,
        LocalDate checkIn,
        LocalDate checkOut,
        boolean parkingLot,
        CarType carType,
        Double totalValue,
        @Enumerated(EnumType.STRING)
        CheckOutTime checkOutTime,

        UserResponseDTO user,
        RoomResponseDTO room
) {
    public BookingResponseDTO(Booking booking) {
        this(booking.getId(), booking.getCheckIn(), booking.getCheckOut(), booking.isParkingLot(), booking.getCarType(), booking.getTotalValue(), booking.getCheckOutTime(), new UserResponseDTO(booking.getUser()), new RoomResponseDTO(booking.getRoom()));
    }
}
