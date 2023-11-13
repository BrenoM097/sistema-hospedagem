package com.br.sistemahospedagem.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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

    public void saveNewRoom(Room newRoom) {
        repository.save(newRoom);
    }

    public boolean checkAvailability(Booking lastestBookingByRoomId) {
        String checkOutTime = lastestBookingByRoomId.getCheckOutTime().name();
        int checkOut = 0;
        if (checkOutTime.equals("MEIODIA")) {
            checkOut = 12;
        } else if (checkOutTime.equals("TARDE")) {
            checkOut = 18;
        } else if (checkOutTime.equals("NOITE")) {
            checkOut = 22;
        }
        LocalDate checkOutDate = lastestBookingByRoomId.getCheckOut();

        ZonedDateTime now = Instant.now().atZone(ZoneId.systemDefault());
        ZonedDateTime currentDate = ZonedDateTime.now(ZoneId.systemDefault()); 
        LocalDate currentLocalDate = currentDate.toLocalDate();
        LocalTime currentTime = now.toLocalTime();
        LocalTime checkOutHour = LocalTime.of(checkOut, 0);

        boolean hourValid = currentTime.isAfter(checkOutHour);
        boolean dateValid = currentLocalDate.isAfter(checkOutDate);
        
        if(dateValid && hourValid) return true;

        return false;
    }
}
