package com.br.sistemahospedagem.repositories;

import com.br.sistemahospedagem.domain.room.BedType;
import com.br.sistemahospedagem.domain.room.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import com.br.sistemahospedagem.domain.booking.Booking;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import org.junit.jupiter.api.DisplayName;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class BookingRepositoryTest {
    private static final Logger LOGGER = Logger.getLogger(BookingRepositoryTest.class.getName());

    @Mock
    BookingRepository bookingRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve retornar uma reserva entre as datas informadas")
    void testFindBookingsByDatesWhenExistRoom() {
        LocalDate checkIn = LocalDate.of(2023, 11, 14);
        LocalDate checkOut = LocalDate.of(2023, 11, 18);

        Booking mockedBooking = new Booking();
        mockedBooking.setCheckIn(checkIn);
        mockedBooking.setCheckOut(checkOut);

        List<Booking> result = Collections.singletonList(mockedBooking);

        LOGGER.info("Executando o teste com checkIn: " + checkIn + " e checkOut: " + checkOut);

        // Configurando o mock para retornar a lista result quando o método é chamado
        when(bookingRepository.findBookingsByDates(checkIn, checkOut)).thenReturn(result);

        List<Booking> bookings = bookingRepository.findBookingsByDates(checkIn, checkOut);

        LOGGER.info("Número de reservas encontradas: " + bookings.size());

        assertThat(bookings).isNotEmpty().contains(mockedBooking);
    }

    @Test
    @DisplayName("Deve retornar nada pois nao ha uma reserva entre as datas informadas")
    void testFindBookingsByDatesWhenNotExistRoom() {
        LocalDate checkIn = LocalDate.of(2023, 11, 14);
        LocalDate checkOut = LocalDate.of(2023, 11, 18);

        //Lista vazia simulando a não ocorrência de nenhuma reserva entre as datas estabelecidas
        List<Booking> result = Collections.emptyList();

        LOGGER.info("Executando o teste com checkIn: " + checkIn + " e checkOut: " + checkOut);

        // Configurando o mock para retornar a lista result quando o método é chamado
        when(bookingRepository.findBookingsByDates(checkIn, checkOut)).thenReturn(result);

        List<Booking> bookings = bookingRepository.findBookingsByDates(checkIn, checkOut);

        assertThat(bookings).isEmpty();
    }

    @Test
    @DisplayName("Deve retornar a ultima reserva com base em um ID de quarto")
    void findLatestBookingByRoomId() {
        int roomId = 2;
        LOGGER.info("Executando o teste com o id de quarto: " + roomId);

        Room mockedRoom = new Room(roomId, 2, 2, BedType.DOUBLE, false, 30.0);
        Booking mockedBooking = new Booking();
        mockedBooking.setRoom(mockedRoom);

        when(bookingRepository.findLatestBookingByRoomId(roomId)).thenReturn(mockedBooking);
        Booking result = bookingRepository.findLatestBookingByRoomId(roomId);

        assertThat(result)
                .as("Verifica se a reserva encontrada não é nula")
                .isNotNull();

        assertThat(result.getRoom().getId())
                .as("Verifica se o ID do quarto na reserva corresponde ao ID fornecido")
                .isEqualTo(roomId);
    }

    @Test
    void findLatestBookingByRoomIdWhenNotExist() {
        int roomId = 2;
        LOGGER.info("Executando o teste com o id de quarto: " + roomId);

        when(bookingRepository.findLatestBookingByRoomId(roomId)).thenReturn(null);
        Booking result = bookingRepository.findLatestBookingByRoomId(roomId);

        assertThat(result)
                .as("Verifica se a reserva encontrada é nula")
                .isNull();
    }
}