package com.br.sistemahospedagem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.br.sistemahospedagem.domain.cliente.Cliente;
import com.br.sistemahospedagem.dtos.ClienteDTO;
import com.br.sistemahospedagem.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    ClienteService clienteService;
    
    @PostMapping
    public ResponseEntity<Cliente> fazerReserva(@RequestBody ClienteDTO cliente) {
        Cliente newCliente = clienteService.criarReserva(cliente);  
        return new ResponseEntity<>(newCliente, HttpStatus.CREATED);
        
    }
}
