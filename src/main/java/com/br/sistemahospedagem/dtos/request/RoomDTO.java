package com.br.sistemahospedagem.dtos.request;

import com.br.sistemahospedagem.infra.schemas.room.BedType;

public record RoomDTO(int adults, int floor, BedType bedType, boolean haveArConditioner, Double dailyValue) {}
