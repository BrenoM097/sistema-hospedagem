package com.br.sistemahospedagem.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.br.sistemahospedagem.domain.booking.Booking;
import com.br.sistemahospedagem.domain.room.Room;

public interface BookingRepository extends JpaRepository<Booking, Long>{
    Optional<Booking> findClienteByRoom(Room roomId);
    Optional<Booking> findClienteById(Long id);
    @Query("SELECT c FROM bookings_table c WHERE c.checkIn >= :checkIn AND c.checkOut <= :checkOut")
    List<Booking> findBookingsByDates(@Param("checkIn") LocalDateTime checkIn, @Param("checkOut") LocalDateTime checkOut);
    
} 
