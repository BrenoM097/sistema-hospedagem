package com.br.sistemahospedagem.dtos.response;

import com.br.sistemahospedagem.domain.pojos.Room;
import com.br.sistemahospedagem.infra.schemas.room.BedType;

public record RoomResponseDTO(
         int id,
         int adults,
         int floor,
         BedType bedType,
         boolean haveArConditioner,
         Double dailyValue
) {
    public RoomResponseDTO(Room room) {
        this(room.getId(), room.getAdults(), room.getFloor(), room.getBedType(), room.isHaveArConditioner(), room.getDailyValue());
    }
}
