package com.br.sistemahospedagem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.sistemahospedagem.domain.cliente.Cliente;
import com.br.sistemahospedagem.dtos.ClienteDTO;
import com.br.sistemahospedagem.repositories.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public Cliente criarReserva(ClienteDTO cliente) {
        Cliente newReserva = new Cliente(cliente);
        this.salvarReserva(newReserva);
        return newReserva;
    }

    public void salvarReserva(Cliente cliente) {
        this.clienteRepository.save(cliente);
    }
    
}
