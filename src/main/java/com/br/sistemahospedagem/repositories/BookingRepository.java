package com.br.sistemahospedagem.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.br.sistemahospedagem.domain.booking.Booking;
import com.br.sistemahospedagem.domain.room.Room;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{
    Optional<Booking> findBookingByRoom(Room roomId);
    Optional<Booking> findBookingById(Long id);
    @Query("SELECT r FROM bookings_table r WHERE r.checkIn >= :checkIn AND r.checkOut <= :checkOut")
    List<Booking> findBookingsByDates(@Param("checkIn") LocalDate checkIn, @Param("checkOut") LocalDate checkOut);
    @Query(value = "SELECT * FROM bookings_table WHERE room_id = ?1 ORDER BY booking_id DESC LIMIT 1", nativeQuery = true)
    Booking findLatestBookingByRoomId(int roomId);
} 
