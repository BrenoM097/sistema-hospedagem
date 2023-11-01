package com.br.sistemahospedagem.domain.cliente;

import java.time.LocalDateTime;
import com.br.sistemahospedagem.domain.quarto.Quarto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tabela-clientes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clienteId;

    @Column(name = "nome")
    private String primeiroNome;
    private String sobrenome;
    private String email;
    private String cpf;

    @Column(name = "data-chegada")
    private LocalDateTime dataChegada;
    @Column(name = "data-saida")
    private LocalDateTime dataSaida;
    @Column(name = "requer-estacionamento")
    private boolean requerEstacionamento;
    
    private CarroTipo carroTipo;

    @ManyToOne
    @JoinColumn(name = "quartoId")
    private Quarto quarto;

    private Double valorTotal;

}
