package com.br.sistemahospedagem.domain.pojos;

import com.br.sistemahospedagem.infra.schemas.booking.CarType;
import com.br.sistemahospedagem.infra.schemas.booking.CheckOutTime;
import com.br.sistemahospedagem.infra.schemas.email.StatusEmail;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Booking {
    private Long id;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private int totalDays;
    private Double totalValue;
    private User user;
    private Room room;
    private StatusEmail emailStatus;

    private boolean parkingLot;
    private CarType carType;

    private CheckOutTime checkOutTime;

    public Booking(LocalDate checkIn, LocalDate checkOut, User user, boolean parkingLot, CarType carType, CheckOutTime checkOutTime) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.user = user;
        this.parkingLot = parkingLot;
        this.carType = carType;
        this.checkOutTime = checkOutTime;
    }

    private void setTotalDays() {
        this.totalDays = this.checkOut.getDayOfMonth() - this.checkIn.getDayOfMonth();
    }

    public void calculateTotalValue(Room room) {
        this.setTotalDays();
        this.totalValue =  this.totalDays * room.getDailyValue();
    }

    public void confirmEmailSent() {
        this.emailStatus = StatusEmail.SENT;
    }

    public void flagEmailError() {
        this.emailStatus = StatusEmail.ERROR;
    }
}
