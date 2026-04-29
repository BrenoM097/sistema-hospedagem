package com.br.sistemahospedagem.infra.schemas.room;

import com.br.sistemahospedagem.dtos.request.RoomDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name = "rooms_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomSchema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private int id;
    private int adults;
    private int floor;
    @Enumerated(EnumType.STRING)
    private BedType bedType;
    private boolean haveArConditioner;
    private Double dailyValue;

    public RoomSchema(RoomDTO data) {
        this.adults = data.adults();
        this.floor = data.floor();
        this.bedType = data.bedType();
        this.haveArConditioner = data.haveArConditioner();
        this.dailyValue = data.dailyValue();
    }

}
