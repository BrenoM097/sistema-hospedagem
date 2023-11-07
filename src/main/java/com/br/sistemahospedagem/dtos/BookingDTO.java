package com.br.sistemahospedagem.dtos;

import java.time.LocalDate;
import com.br.sistemahospedagem.domain.booking.CarType;
import com.br.sistemahospedagem.domain.room.Room;

public record BookingDTO(String firstName, String lastName, String email, String cpf, LocalDate checkIn, LocalDate checkOut, boolean parkingLot, CarType carType, Room room, Double totalValor) {}
