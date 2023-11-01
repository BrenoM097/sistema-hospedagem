package com.br.sistemahospedagem.domain.estacionamento;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tabela-estacionamento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estacionamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int vagasCarros = 30;
    private int totalVagas = 39;
    private Double valorDiaria = 20.0;
    private int vagasVan = 5;
    private int vagasMicro = 3;
    private int vagasOnibus = 1;
   
}
