package com.br.sistemahospedagem.dtos;

import java.time.LocalDate;
import com.br.sistemahospedagem.domain.booking.CarType;
import com.br.sistemahospedagem.domain.booking.CheckOutTime;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    private Long id;
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String cpf;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private boolean parkingLot;
    private CarType carType;
    private int roomId;
    private Double totalValue;
    @Enumerated(EnumType.STRING)
    private CheckOutTime checkOutTime;

}
