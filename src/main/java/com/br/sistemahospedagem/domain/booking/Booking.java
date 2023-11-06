package com.br.sistemahospedagem.domain.booking;

import java.time.LocalDateTime;
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
import lombok.Setter;

@Entity(name = "bookings_table")
@Getter
@Setter
@AllArgsConstructor
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
    private LocalDateTime checkIn;
    @Column(name = "checkOut")
    private LocalDateTime checkOut;
    @Column(name = "parkingLot")
    private boolean parkingLot;
    
    @Enumerated(EnumType.STRING)
    private CarType carType;

    @ManyToOne
    @JoinColumn(name = "room_Id")
    private Room room;

    private Double totalValor;

    public Booking(BookingDTO data) {
        this.firstName = data.firstName();
        this.lastName = data.lastName();
        this.email = data.email();
        this.cpf = data.cpf();
        this.checkIn = data.checkIn();
        this.checkOut = data.checkOut();
        this.parkingLot = data.parkingLot();
        this.carType = data.carType();
        this.room = data.room();
        this.totalValor = data.totalValor();
    }

}
