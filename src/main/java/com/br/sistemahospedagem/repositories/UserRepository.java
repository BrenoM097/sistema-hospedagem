package com.br.sistemahospedagem.repositories;

import com.br.sistemahospedagem.infra.schemas.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
   UserDetails findUserByEmail(String email);
}
