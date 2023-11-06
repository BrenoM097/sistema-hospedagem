package com.br.sistemahospedagem.domain.parking;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "parkings_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int carSpaces = 30;
    private int totalSpaces = 39;
    private Double valorDiaria = 20.0;
    private int vanSpaces = 5;
    private int microSpaces = 3;
    private int busSpace = 1;
   
}
