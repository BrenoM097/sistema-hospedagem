package com.br.sistemahospedagem.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.sistemahospedagem.domain.booking.Booking;
import com.br.sistemahospedagem.domain.booking.CheckOutTime;
import com.br.sistemahospedagem.domain.room.Room;
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

    public boolean isThisRoomAvailable(Booking latestBooking) {
    if (latestBooking == null) {
            return true; // Se não houver reservas anteriores, o quarto está disponível
        }

        LocalDate checkOutDate = latestBooking.getCheckOut();
        CheckOutTime checkOutTime = latestBooking.getCheckOutTime();

        int checkOutHour = 0;
        if (checkOutTime == CheckOutTime.MEIODIA) {
            checkOutHour = 12;
        } else if (checkOutTime == CheckOutTime.TARDE) {
            checkOutHour = 18;
        } else if (checkOutTime == CheckOutTime.NOITE) {
            checkOutHour = 22;
        }

        ZonedDateTime currentDateTime = ZonedDateTime.now(ZoneId.systemDefault());
        ZonedDateTime checkOutDateTime = checkOutDate.atTime(checkOutHour, 0).atZone(ZoneId.systemDefault());

        return currentDateTime.isAfter(checkOutDateTime) || currentDateTime.isEqual(checkOutDateTime);
}

    public List<Room> getAllAvailableRooms() {
        List<Room> allRooms = repository.findAll();
        List<Room> allAvaiableRooms = new ArrayList<>();
       
        for(Room room : allRooms) {
            Booking lastestBookingByRoomId = bookingRepository.findLatestBookingByRoomId(room.getId());
            boolean available = isThisRoomAvailable(lastestBookingByRoomId);
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
