package com.br.sistemahospedagem.service;

import com.br.sistemahospedagem.infra.schemas.user.UserModel;
import com.br.sistemahospedagem.dtos.request.UserRequestDTO;
import com.br.sistemahospedagem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserModel saveUser(UserRequestDTO user) {
        String encodedPass = new BCryptPasswordEncoder().encode(user.getPassword());
        UserModel newUser = new UserModel(user);
        newUser.setPassword(encodedPass);
        return userRepository.save(newUser);
    }

    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    public UserModel findUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserDetails findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

}
