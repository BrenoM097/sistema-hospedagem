package com.br.sistemahospedagem.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.sistemahospedagem.domain.booking.Booking;
import com.br.sistemahospedagem.domain.room.Room;
import com.br.sistemahospedagem.exceptions.RoomNotFoundException;
import com.br.sistemahospedagem.repositories.BookingRepository;
import com.br.sistemahospedagem.repositories.RoomRepository;

@Service
public class RoomService {
    @Autowired
    RoomRepository repository;

    @Autowired
    BookingRepository bookingRepository;

    public Room findRoomById(int roomId) {
        return repository.findRoomByid(roomId).orElse(null);
    }

    public void saveNewRoom(Room newRoom) {
        repository.save(newRoom);
    }

    public boolean checkAvailabilityOfRoom(Booking bookingRoom) {
        String checkOutTime = bookingRoom.getCheckOutTime().name();
        int checkOut = 0;
        if (checkOutTime.equals("MEIODIA")) {
            checkOut = 12;
        } else if (checkOutTime.equals("TARDE")) {
            checkOut = 18;
        } else if (checkOutTime.equals("NOITE")) {
            checkOut = 22;
        }
        LocalDate checkOutDate = bookingRoom.getCheckOut();

        ZonedDateTime now = Instant.now().atZone(ZoneId.systemDefault());
        ZonedDateTime currentDate = ZonedDateTime.now(ZoneId.systemDefault()); 
        LocalDate currentLocalDate = currentDate.toLocalDate();
        LocalTime currentTime = now.toLocalTime();
        LocalTime checkOutHour = LocalTime.of(checkOut, 0);

        boolean dateValid = currentLocalDate.isAfter(checkOutDate) || 
                        (currentLocalDate.isEqual(checkOutDate) && currentTime.isAfter(checkOutHour));
        
        return dateValid;
    }

    public List<Room> getAllAvailableRooms() {
        List<Room> allRooms = repository.findAll();
        List<Room> allAvaiableRooms = new ArrayList<>();
       
        for(Room room : allRooms) {
            Booking lastestBookingByRoomId = bookingRepository.findLatestBookingByRoomId(room.getId());
            boolean available = checkAvailabilityOfRoom(lastestBookingByRoomId);
            if(available) {
                allAvaiableRooms.add(room);
            }
        }
        if(!allAvaiableRooms.isEmpty()) {
            return allAvaiableRooms;
        }
        throw new RoomNotFoundException("Nenhum quarto foi encontrado");
    }
}
