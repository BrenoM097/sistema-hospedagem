package com.br.sistemahospedagem.dtos.request;

import com.br.sistemahospedagem.enums.UserRole;

public record UserRequestDTO (
        int id,
        String firstName,
        String lastName,
        String cpf,
        String email,
        String phone,
        String address,
        String password,
        UserRole role
){}
