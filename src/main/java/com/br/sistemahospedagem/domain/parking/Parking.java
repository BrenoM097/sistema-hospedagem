package com.br.sistemahospedagem.domain.parking;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name = "parkings_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int carSpaces;
    private int totalSpaces;
    private Double valorDiaria;
    private int vanSpaces;
    private int microSpaces;
    private int busSpace;

}
