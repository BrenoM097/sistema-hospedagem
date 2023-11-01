package com.br.sistemahospedagem.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.br.sistemahospedagem.domain.cliente.Cliente;
import com.br.sistemahospedagem.domain.quarto.Quarto;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    Optional<Cliente> findClienteByQuarto(Quarto quartoId);
    Optional<Cliente> findClienteById(Long id);
    
} 
