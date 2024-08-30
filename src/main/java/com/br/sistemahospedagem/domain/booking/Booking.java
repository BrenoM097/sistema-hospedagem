package com.br.sistemahospedagem.domain.booking;

import com.br.sistemahospedagem.domain.email.StatusEmail;
import com.br.sistemahospedagem.domain.room.Room;
import com.br.sistemahospedagem.domain.user.User;
import com.br.sistemahospedagem.dtos.BookingDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "bookings_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "check_in")
    private LocalDate checkIn;
    @Column(name = "check_out")
    private LocalDate checkOut;
    @Column(name = "parking_lot")
    private boolean parkingLot;
    @Enumerated(EnumType.STRING)
    private CarType carType;
    @Enumerated(EnumType.STRING)
    private CheckOutTime checkOutTime;
    @ManyToOne
    @JoinColumn(name = "room_Id", nullable = false)
    private Room room;
    private Double totalValue;
    private StatusEmail statusEmail;

    public Booking(BookingDTO data) {
        this.id = data.getId();
        this.checkIn = data.getCheckIn();
        this.checkOut = data.getCheckOut();
        this.parkingLot = data.isParkingLot();
        this.carType = data.getCarType();
        this.totalValue = data.getTotalValue();
        this.checkOutTime = data.getCheckOutTime();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Booking booking = (Booking) o;
        return id != null && Objects.equals(id, booking.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
