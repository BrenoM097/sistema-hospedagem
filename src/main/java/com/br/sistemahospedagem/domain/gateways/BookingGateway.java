package com.br.sistemahospedagem.domain.gateways;

import com.br.sistemahospedagem.domain.pojos.Booking;

import java.util.Optional;

public interface BookingGateway {
    Booking save(Booking booking);
    Booking findLatestBookingByRoomId(Integer roomId);
    Optional<Booking> findById(Long id);
}
