package com.br.sistemahospedagem.controller;

import com.br.sistemahospedagem.domain.booking.Booking;
import com.br.sistemahospedagem.domain.room.BedType;
import com.br.sistemahospedagem.domain.room.Room;
import com.br.sistemahospedagem.dtos.RoomDTO;
import com.br.sistemahospedagem.service.BookingService;
import com.br.sistemahospedagem.service.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.HttpServerErrorException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class RoomControllerTest {
    private static final Logger LOGGER = Logger.getLogger(RoomControllerTest.class.getName());
    @Mock
    private BookingService bookingService;

    @Mock
    private RoomService roomService;

    @InjectMocks
    private RoomController roomController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIsThisRoomAvailableWhenAvailable() {
        int roomId = 1; // Define o ID do quarto para teste

        Booking mockedBooking = new Booking(); // Crie um objeto Booking simulado
        mockedBooking.setCheckOut(LocalDate.of(2023,12,31)); // Defina um check-out simulado para o objeto Booking

        when(bookingService.findLatestBookingByRoomId(roomId)).thenReturn(mockedBooking);
        when(roomService.isRoomAvailable(mockedBooking)).thenReturn(true);

        ResponseEntity<CustomResponse> responseEntity = roomController.isThisRoomAvailable(roomId);
        LOGGER.info("Retorno do teste mockado: " + responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("DISPONIVEL/2023-12-31", Objects.requireNonNull(responseEntity.getBody()).getMessage());
    }

    @Test
    void testIsThisRoomAvailableWhenNotAvailable() {
        int roomId = 1; // Define o ID do quarto para teste

        Booking mockedBooking = new Booking(); // Crie um objeto Booking simulado
        mockedBooking.setCheckOut(LocalDate.of(2023,12,31)); // Defina um check-out simulado para o objeto Booking

        when(bookingService.findLatestBookingByRoomId(roomId)).thenReturn(mockedBooking);
        when(roomService.isRoomAvailable(mockedBooking)).thenReturn(false);

        ResponseEntity<CustomResponse> responseEntity = roomController.isThisRoomAvailable(roomId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("INDISPONIVEL/2023-12-31", Objects.requireNonNull(responseEntity.getBody()).getMessage());
    }

    @Test
    void testIsThisRoomAvailableWhenNotFound() {
        int roomId = 1; // Define o ID do quarto para teste

        when(bookingService.findLatestBookingByRoomId(roomId)).thenReturn(null);

        ResponseEntity<CustomResponse> responseEntity = roomController.isThisRoomAvailable(roomId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Deve cadastrar um novo quarto com sucesso")
    void testCreateRoomSuccess() {
        RoomDTO roomDTO = new RoomDTO(2, 2, BedType.DOUBLE, false, 30.0);
        Room newRoom = new Room(roomDTO);
        roomController.createRoom(roomDTO);

        // Verifica se o método saveNewRoom do serviço foi chamado exatamente uma vez com o quarto correto
        verify(roomService, times(1)).saveNewRoom(newRoom);
    }

    @Test
    @DisplayName("Não deve cadastrar um novo quarto com sucesso")
    void testCreateRoomFailed() {
        RoomDTO roomDTO = new RoomDTO(2, 2, BedType.DOUBLE, false, 30.0);

        when(roomController.createRoom(roomDTO)).thenThrow(HttpServerErrorException.InternalServerError.class);

        assertThrows(HttpServerErrorException.InternalServerError.class, () -> roomController.createRoom(roomDTO));
    }

    @Test
    @DisplayName("Deve retornar os quartos disponiveis")
    void testAvailableRooms() {

        RoomDTO roomDTO = new RoomDTO(2, 2, BedType.DOUBLE, false, 30.0);
        RoomDTO roomDTO2 = new RoomDTO(3, 1, BedType.DOUBLE, true, 70.0);
        Room newRoom = new Room(roomDTO);
        Room newRoom2 = new Room(roomDTO2);

        List<Room> mockAvailableRooms = Arrays.asList(
                newRoom,
                newRoom2
        );

        // Configurar comportamento do mock do RoomService
        when(roomService.getAllAvailableRooms()).thenReturn(mockAvailableRooms);

        ResponseEntity<List<Room>> responseEntity = roomController.availableRooms();

        // Verificar se o método do RoomService foi chamado
        verify(roomService).getAllAvailableRooms();

        // Verificar se a resposta do controlador está correta
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockAvailableRooms, responseEntity.getBody());
    }

    @Test
    @DisplayName("Deve retornar uma lista vazia pois não há quartos disponiveis")
    void testAvailableRoomsWhenNoOneIsAvailable() {

        // Configurar comportamento do mock do RoomService
        when(roomService.getAllAvailableRooms()).thenReturn(new ArrayList<>());

        ResponseEntity<List<Room>> responseEntity = roomController.availableRooms();

        // Verificar se o método do RoomService foi chamado
        verify(roomService).getAllAvailableRooms();

        // Verificar se a resposta do controlador está correta
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(Objects.requireNonNull(responseEntity.getBody()).isEmpty(), "A lista de quartos disponíveis não está vazia como esperado.");
    }

}


