package com.br.sistemahospedagem.dtos;

import com.br.sistemahospedagem.domain.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String cpf;
    private String email;
    private String phone;
    private String address;
    private String password;
    private UserRole role;
}
