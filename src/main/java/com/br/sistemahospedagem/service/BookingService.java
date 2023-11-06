package com.br.sistemahospedagem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.sistemahospedagem.domain.booking.Booking;
import com.br.sistemahospedagem.dtos.BookingDTO;
import com.br.sistemahospedagem.repositories.BookingRepository;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    public Booking reserve(BookingDTO booking) {
        Booking newReserva = new Booking(booking);
        newReserva.setTotalValor(calcValor(newReserva));
        this.saveReserve(newReserva);
        return newReserva;
    }

    public void saveReserve(Booking booking) {
        this.bookingRepository.save(booking);
    }
    public Double calcValor(Booking booking) {
        int dias = booking.getCheckOut().getDayOfMonth() - booking.getCheckIn().getDayOfMonth();
        return dias * booking.getRoom().getDailyValor();
    }
    
}
