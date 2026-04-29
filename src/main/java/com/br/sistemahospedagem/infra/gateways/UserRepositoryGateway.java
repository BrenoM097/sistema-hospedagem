package com.br.sistemahospedagem.infra.gateways;

import com.br.sistemahospedagem.domain.gateways.UserGateway;
import com.br.sistemahospedagem.domain.pojos.User;
import com.br.sistemahospedagem.exceptions.NotFoundException;
import com.br.sistemahospedagem.infra.schemas.user.UserModel;
import com.br.sistemahospedagem.mappers.PojoEntityMapper;
import com.br.sistemahospedagem.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryGateway implements UserGateway {
    private final UserRepository repository;
    private final PojoEntityMapper mapper;

    public UserRepositoryGateway(UserRepository repository, PojoEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public User findUserById(Integer id) {
        UserModel user = repository.findById(id).orElseThrow(() -> new NotFoundException("User Not Found."));
        return mapper.UserSwapper(user);
    }
}
