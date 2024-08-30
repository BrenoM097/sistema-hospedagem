package com.br.sistemahospedagem.repositories;

import com.br.sistemahospedagem.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
   UserDetails findUserByEmail(String email);
}
