package com.br.sistemahospedagem.domain.estacionamento;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tabela-estacionamento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estacionamento {
    private int vagasCarros;
    private int totalVagas;
    private int valorDiaria;
    private int vagasVan;
    private int vagasMicro;
    private int vagasOnibus;
   
}
