package com.br.sistemahospedagem.dtos;

import com.br.sistemahospedagem.domain.room.BedType;

public record RoomDTO(int adults, int floor, BedType bedType, boolean haveArConditioner, Double dailyValue) {}
