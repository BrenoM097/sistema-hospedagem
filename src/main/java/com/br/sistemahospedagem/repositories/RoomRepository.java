package com.br.sistemahospedagem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.br.sistemahospedagem.infra.schemas.room.RoomSchema;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<RoomSchema, Integer> {
    Optional<RoomSchema> findRoomByid(int id);
}
