package com.br.sistemahospedagem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.sistemahospedagem.domain.booking.Booking;
import com.br.sistemahospedagem.domain.room.Room;
import com.br.sistemahospedagem.dtos.BookingDTO;
import com.br.sistemahospedagem.repositories.BookingRepository;

@Service
public class BookingService {
    @Autowired
    RoomService roomService;

    @Autowired
    BookingRepository bookingRepository;

    public Booking reserve(BookingDTO booking) {
        Room existingRoom = roomService.findRoomById(booking.getRoomId());
        Booking newReserva = new Booking(booking);
        newReserva.setRoom(existingRoom);
        this.saveReserve(newReserva);
            return newReserva;
    }


    public void saveReserve(Booking booking) {
        this.bookingRepository.save(booking);
    }


    public Double calcValor(Double value, int days) {
        return days * value;
    }

    public int getTotalDays(Booking booking) {
        int dias = booking.getCheckOut().getDayOfMonth() - booking.getCheckIn().getDayOfMonth();
        return dias;
    }
    
}
