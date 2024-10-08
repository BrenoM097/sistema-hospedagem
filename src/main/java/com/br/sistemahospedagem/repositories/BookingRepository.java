package com.br.sistemahospedagem.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.br.sistemahospedagem.domain.booking.Booking;
import com.br.sistemahospedagem.domain.room.Room;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{

    @Modifying
    @Transactional
    @Query(value = "UPDATE bookings_table SET car_type = ?9, check_in = ?6, check_out = ?7, cpf = ?5, email = ?4, first_name = ?2, last_name = ?3, parking_lot = ?8, room_id = ?11, check_out_time = ?10 WHERE booking_id = ?1", nativeQuery = true)
    void updateBooking(Long id, String firstName, String lastName, String email, String cpf, LocalDate checkIn, LocalDate checkOut, boolean parkingLot, String carType, String checkOutTime, int roomId);

    Optional<Booking> findBookingByRoom(Room roomId);
    Optional<Booking> findBookingById(Long id);

    //@Query(value = "SELECT bt.booking_id, bt.room_id, bt.check_in, bt.check_out, bt.parking_lot, bt.car_type, bt.total_value, bt.status_email, bt.check_out_time, ut.first_name, ut.last_name, ut.email, ut.phone FROM bookings_table AS bt INNER JOIN users_table as ut ON bt.user_id = ut.user_id", nativeQuery = true)
    List<Booking> findAll();
    @Query("SELECT r FROM bookings_table r WHERE r.checkIn >= :checkIn AND r.checkOut <= :checkOut")
    List<Booking> findBookingsByDates(@Param("checkIn") LocalDate checkIn, @Param("checkOut") LocalDate checkOut);
    @Query(value = "SELECT * FROM bookings_table WHERE room_id = ?1 ORDER BY booking_id DESC LIMIT 1", nativeQuery = true)
    Booking findLatestBookingByRoomId(int roomId);
} 
