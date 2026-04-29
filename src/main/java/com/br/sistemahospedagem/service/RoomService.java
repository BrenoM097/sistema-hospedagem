package com.br.sistemahospedagem.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.sistemahospedagem.infra.schemas.booking.BookingModel;
import com.br.sistemahospedagem.infra.schemas.room.RoomSchema;
import com.br.sistemahospedagem.repositories.BookingRepository;
import com.br.sistemahospedagem.repositories.RoomRepository;

@Service
public class RoomService {
    @Autowired
    RoomRepository repository;

    @Autowired
    BookingRepository bookingRepository;

    public RoomSchema findRoomById(int roomId) {
        return repository.findRoomByid(roomId).orElse(null);
    }

    public void saveNewRoom(RoomSchema newRoom) {
        repository.save(newRoom);
    }

    public List<RoomSchema> getAllAvailableRooms() {
        List<RoomSchema> allRooms = repository.findAll();
        List<RoomSchema> allAvaiableRooms = new ArrayList<>();
       
        for(RoomSchema room : allRooms) {
            BookingModel lastestBookingModelByRoomId = bookingRepository.findLatestBookingByRoomId(room.getId());
            boolean available = isThisRoomAvailable(lastestBookingModelByRoomId);
            if(available) {
                allAvaiableRooms.add(room);
            }
        }
        if(!allAvaiableRooms.isEmpty()) {
            return allAvaiableRooms;
        }
        System.out.println("Todos quartos ocupados.");
        return new ArrayList<>();
    }
}
