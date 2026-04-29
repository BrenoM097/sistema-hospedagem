package com.br.sistemahospedagem.domain.services;

import com.br.sistemahospedagem.domain.pojos.Booking;
import com.br.sistemahospedagem.infra.schemas.booking.CheckOutTime;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class RoomAvailabilityCheck {
    public static boolean checkRoom(Booking booking) {
        if (booking == null) {
            return true; // Se não houver reservas anteriores, o quarto está disponível
        }

        LocalDate checkOutDate = booking.getCheckOut();
        CheckOutTime checkOutTime = booking.getCheckOutTime();

        int checkOutHour = 0;
        if (checkOutTime == CheckOutTime.MEIODIA) {
            checkOutHour = 12;
        } else if (checkOutTime == CheckOutTime.TARDE) {
            checkOutHour = 18;
        } else if (checkOutTime == CheckOutTime.NOITE) {
            checkOutHour = 22;
        }

        ZonedDateTime currentDateTime = ZonedDateTime.now(ZoneId.systemDefault());
        ZonedDateTime checkOutDateTime = checkOutDate.atTime(checkOutHour, 0).atZone(ZoneId.systemDefault());

        return currentDateTime.isAfter(checkOutDateTime) || currentDateTime.isEqual(checkOutDateTime);
    }
}
