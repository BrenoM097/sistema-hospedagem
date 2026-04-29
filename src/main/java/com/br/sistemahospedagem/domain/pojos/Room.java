package com.br.sistemahospedagem.domain.pojos;

import com.br.sistemahospedagem.infra.schemas.room.BedType;
import lombok.Data;

@Data
public class Room {
    private int id;
    private int adults;
    private int floor;
    private BedType bedType;
    private boolean haveArConditioner;
    private Double dailyValue;
}
