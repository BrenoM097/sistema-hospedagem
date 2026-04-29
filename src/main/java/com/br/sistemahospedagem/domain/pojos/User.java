package com.br.sistemahospedagem.domain.pojos;

import com.br.sistemahospedagem.enums.UserRole;
import lombok.Data;

@Data
public class User {
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
