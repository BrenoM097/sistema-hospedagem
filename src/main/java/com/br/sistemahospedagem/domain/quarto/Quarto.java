package com.br.sistemahospedagem.domain.quarto;

import com.br.sistemahospedagem.domain.estacionamento.Estacionamento;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tabela-quartos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quarto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int quartoId;
    private int qtLeitos;
    private int andar;
    private CamaTipo camaTipo;
    private boolean possuiArCondicionado;
    private Double valorDiaria;

    @OneToOne
    @JoinColumn(name = "estacionamentoId")
    private Estacionamento estacionamento;

}
