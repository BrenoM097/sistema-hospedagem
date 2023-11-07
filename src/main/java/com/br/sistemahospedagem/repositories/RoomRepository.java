package com.br.sistemahospedagem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.br.sistemahospedagem.domain.room.Room;
import java.util.Optional;


public interface RoomRepository extends JpaRepository<Room, Integer> {
    Optional<Room> findRoomById(int id);
}
