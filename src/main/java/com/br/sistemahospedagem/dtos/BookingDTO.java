package com.br.sistemahospedagem.dtos;

import java.time.LocalDate;
import com.br.sistemahospedagem.domain.booking.CarType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String cpf;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private boolean parkingLot;
    private CarType carType;
    private int roomId;
    private Double totalValor;

}
