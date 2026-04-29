package com.br.sistemahospedagem.domain.gateways;

import com.br.sistemahospedagem.domain.pojos.User;


public interface UserGateway {
    User findUserById(Integer id);
}
