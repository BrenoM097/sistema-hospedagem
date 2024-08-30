package com.br.sistemahospedagem.service;

import com.br.sistemahospedagem.domain.user.User;
import com.br.sistemahospedagem.dtos.UserDTO;
import com.br.sistemahospedagem.repositories.UserRepository;
import com.br.sistemahospedagem.security.Crypt;
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
    public User saveUser(UserDTO user) {
        String encodedPass = new BCryptPasswordEncoder().encode(user.getPassword());
        User newUser = new User(user);
        newUser.setPassword(encodedPass);
        return userRepository.save(newUser);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserDetails findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

}
