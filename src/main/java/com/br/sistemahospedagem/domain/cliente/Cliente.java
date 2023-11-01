package com.br.sistemahospedagem.domain.cliente;

import java.time.LocalDateTime;
import com.br.sistemahospedagem.domain.quarto.Quarto;
import com.br.sistemahospedagem.dtos.ClienteDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "tabela-clientes")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente-id")
    private Long id;

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
    
    @Enumerated(EnumType.STRING)
    private CarroTipo carroTipo;

    @ManyToOne
    @JoinColumn(name = "quartoId")
    private Quarto quarto;

    private Double valorTotal;

    public Cliente(ClienteDTO data) {
        this.primeiroNome = data.primeiroNome();
        this.sobrenome = data.sobrenome();
        this.email = data.email();
        this.cpf = data.cpf();
        this.dataChegada = data.dataChegada();
        this.dataSaida = data.dataSaida();
        this.requerEstacionamento = data.requerEstacionamento();
        this.carroTipo = data.carroTipo();
        this.quarto = data.quarto();
        this.valorTotal = data.valorTotal();
    }

}
