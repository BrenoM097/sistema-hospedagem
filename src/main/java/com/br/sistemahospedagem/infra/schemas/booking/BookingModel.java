package com.br.sistemahospedagem.infra.schemas.booking;

import com.br.sistemahospedagem.infra.schemas.email.StatusEmail;
import com.br.sistemahospedagem.infra.schemas.room.RoomSchema;
import com.br.sistemahospedagem.infra.schemas.user.UserModel;
import com.br.sistemahospedagem.dtos.request.BookingRequestDTO;
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
public class BookingModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

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
    private RoomSchema room;
    private Double totalValue;
    private StatusEmail statusEmail;

    public BookingModel(BookingRequestDTO data) {
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
        BookingModel bookingModel = (BookingModel) o;
        return id != null && Objects.equals(id, bookingModel.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
