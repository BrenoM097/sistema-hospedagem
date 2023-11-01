package com.br.sistemahospedagem.dtos;

import java.time.LocalDateTime;
import com.br.sistemahospedagem.domain.cliente.CarroTipo;
import com.br.sistemahospedagem.domain.quarto.Quarto;

public record ClienteDTO(String primeiroNome, String sobrenome, String email, String cpf, LocalDateTime dataChegada, LocalDateTime dataSaida, boolean requerEstacionamento, CarroTipo carroTipo, Quarto quarto, Double valorTotal) {}
