package com.br.sistemahospedagem.domain.booking;

import java.time.LocalDate;

import com.br.sistemahospedagem.config.StatusEmail;
import com.br.sistemahospedagem.domain.room.Room;
import com.br.sistemahospedagem.dtos.BookingDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "bookings_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long id;

    @Column(name = "firstName")
    private String firstName;
    private String lastName;
    private String email;
    private String cpf;

    @Column(name = "checkIn")
    private LocalDate checkIn;
    @Column(name = "checkOut")
    private LocalDate checkOut;
    @Column(name = "parkingLot")
    private boolean parkingLot;
    
    @Enumerated(EnumType.STRING)
    private CarType carType;

    @Enumerated(EnumType.STRING)
    private CheckOutTime checkOutTime;

    @ManyToOne
    @JoinColumn(name = "room_Id")
    private Room room;

    private Double totalValue;

    private StatusEmail statusEmail;

    public Booking(BookingDTO data) {
        this.firstName = data.getFirstName();
        this.lastName = data.getLastName();
        this.email = data.getEmail();
        this.cpf = data.getCpf();
        this.checkIn = data.getCheckIn();
        this.checkOut = data.getCheckOut();
        this.parkingLot = data.isParkingLot();
        this.carType = data.getCarType();
        this.totalValue = data.getTotalValue();
        this.checkOutTime = data.getCheckOutTime();
    }

}
