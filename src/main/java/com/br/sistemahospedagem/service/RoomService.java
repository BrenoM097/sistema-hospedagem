package com.br.sistemahospedagem.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.sistemahospedagem.domain.booking.Booking;
import com.br.sistemahospedagem.domain.room.Room;
import com.br.sistemahospedagem.repositories.RoomRepository;

@Service
public class RoomService {
    @Autowired
    RoomRepository repository;

    public Room findRoomById(int roomId) {
        return repository.findRoomByid(roomId).orElse(null);
    }

    public boolean checkAvailability(Booking lastestBookingByRoomId) {
        int checkOutDay = lastestBookingByRoomId.getCheckOut().getDayOfMonth();
        if(checkOutDay < LocalDate.now().getDayOfMonth()) return true;

        return false;
    }
}
