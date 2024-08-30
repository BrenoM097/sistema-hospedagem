package com.br.sistemahospedagem.controller;

import com.br.sistemahospedagem.domain.user.User;
import com.br.sistemahospedagem.dtos.UserDTO;
import com.br.sistemahospedagem.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api("Controlador para CRUD de usuários")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    public UserController(@Autowired UserService service) {
        this.userService = service;
    }
    @ApiOperation("Cadastrar novo usuário")
    @PostMapping("register")
    public ResponseEntity<User> createUser(@RequestBody @Valid UserDTO user) {
        LOGGER.info("Received user data: {}", user);
        User newUser = userService.saveUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}
