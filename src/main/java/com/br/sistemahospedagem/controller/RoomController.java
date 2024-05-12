package com.br.sistemahospedagem.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.br.sistemahospedagem.domain.booking.Booking;
import com.br.sistemahospedagem.domain.room.Room;
import com.br.sistemahospedagem.dtos.RoomDTO;
import com.br.sistemahospedagem.service.BookingService;
import com.br.sistemahospedagem.service.RoomService;

@RestController
@RequestMapping("/rooms")
@Api("Controlador para requisições sobre quartos")
public class RoomController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookingController.class);
     
    private final BookingService bookingService;
    private final RoomService roomService;
    @Autowired
    public RoomController(BookingService bookingService, RoomService roomService) {
        this.bookingService = bookingService;
        this.roomService = roomService;
    }
    
    //Método para checar se determinado quarto está disponível
    @GetMapping("checkRoom")
    @ApiOperation("Operação que consiste em consultar se um quarto em especifico está disponivel com base em um ID")
    public ResponseEntity<CustomResponse> isThisRoomAvailable(@RequestParam("room_id")int roomId) {
        CustomResponse customResponse = new CustomResponse();
        Booking lastestBookingByRoomId = bookingService.findLatestBookingByRoomId(roomId);
       
       if(lastestBookingByRoomId == null) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }

       boolean available = roomService.isThisRoomAvailable(lastestBookingByRoomId);
       if(available) {
        customResponse.setMessage("DISPONIVEL/" + lastestBookingByRoomId.getCheckOut());
       }else {
        customResponse.setMessage("INDISPONIVEL/" + lastestBookingByRoomId.getCheckOut());
       }
       return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }

    //Método para cadastrar um novo quarto
    @PostMapping("/newRoom")
    @ApiOperation("Operação que consiste em cadastrar um novo quarto")
    public ResponseEntity<Room> createRoom(@RequestBody RoomDTO room) {
        LOGGER.info("Received new room request: {}", room);

        Room newRoom = new Room(room);
        roomService.saveNewRoom(newRoom);
        return new ResponseEntity<>(newRoom, HttpStatus.CREATED);
    }

    //Método que lista todos os quartos disponíveis
    @ApiOperation("Operação que consiste em consultar todos os quartos disponiveis")
    @GetMapping("availableRooms")
    public ResponseEntity<List<Room>> availableRooms() {
        List<Room> allAvailableRooms = roomService.getAllAvailableRooms();
        return new ResponseEntity<>(allAvailableRooms, HttpStatus.OK);
    }
    
    
}
