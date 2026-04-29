package com.br.sistemahospedagem.dtos.response;

import com.br.sistemahospedagem.domain.pojos.User;

public record UserResponseDTO(
        int id,
        String firstName,
        String lastName,
        String cpf,
        String email,
        String phone,
        String address
) {
    public UserResponseDTO(User user) {
        this(user.getId(), user.getFirstName(), user.getLastName(), user.getCpf(), user.getEmail(), user.getPhone(), user.getAddress());
    }
}
