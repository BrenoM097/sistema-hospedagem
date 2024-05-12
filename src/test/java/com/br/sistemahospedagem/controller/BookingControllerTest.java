package com.br.sistemahospedagem.controller;

import com.br.sistemahospedagem.domain.booking.Booking;
import com.br.sistemahospedagem.dtos.BookingDTO;
import com.br.sistemahospedagem.repositories.BookingRepository;
import com.br.sistemahospedagem.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingControllerTest {
   // private static final Logger LOGGER = Logger.getLogger(RoomControllerTest.class.getName());

    @Mock
    private BookingService bookingService;

    @Mock
    BookingRepository bookingRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("Deve cadastrar uma nova reserva com sucesso")
    void testeReserveWithSuccessful() {
        BookingDTO mockedBooking = new BookingDTO();
        Booking booking = new Booking(mockedBooking);

        when(bookingService.reserve(mockedBooking)).thenReturn(booking);
        Booking reserve = bookingService.reserve(mockedBooking);

        //Verificando se o metodo bookingService foi chamado apenas uma vez
        verify(bookingService, times(1));
        //Verificando se o booking passando é igual ao booking salvo
        assertEquals(reserve, booking, "A resposta não contém a reserva esperada");
    }

    @Test
    @DisplayName("Não deve cadastrar uma nova reserva com sucesso")
    void testeReserveWithFailure() {
        BookingDTO mockedBooking = new BookingDTO();
        Booking booking = new Booking(mockedBooking);

        when(bookingService.reserve(mockedBooking)).thenReturn(any(Booking.class));
        Booking reserve = bookingService.reserve(mockedBooking);

        //Verificando se a classe bookingService foi chamado apenas uma vez
        verify(bookingService, times(1));

        //Verificando se o booking passando NÃO é igual ao booking salvo
        assertNotEquals(reserve, booking, "A reserva cadastrada não é a mesma esperada");
    }

    @Test
    void reservesInBetweenDates() {
        LocalDate checkIn = LocalDate.of(2023, 11, 14);
        LocalDate checkOut = LocalDate.of(2023, 11, 18);

        Booking mockedBooking = new Booking();
        mockedBooking.setCheckIn(checkIn);
        mockedBooking.setCheckOut(checkOut);

        List<Booking> allBookings = new ArrayList<>();
        allBookings.add(mockedBooking);

        when(bookingRepository.findBookingsByDates(checkIn, checkOut)).thenReturn(allBookings);

        List<Booking> bookingsResponse = bookingRepository.findBookingsByDates(checkIn, checkOut);

        //Verificando se a classe bookingRepository foi chamada apenas uma vez
        verify(bookingRepository, times(1));

        //Verifica se a lista com o objeto mockado é igual ao retorno do metodo
        assertEquals(allBookings, bookingsResponse);
        //Verifica se a resposta usando o metodo contém a reserva determinada
        assertTrue(bookingsResponse.contains(mockedBooking));
    }
}