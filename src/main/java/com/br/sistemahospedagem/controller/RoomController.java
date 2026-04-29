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
import com.br.sistemahospedagem.infra.schemas.booking.BookingModel;
import com.br.sistemahospedagem.infra.schemas.room.RoomSchema;
import com.br.sistemahospedagem.dtos.request.RoomDTO;
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
        BookingModel lastestBookingModelByRoomId = bookingService.findLatestBookingByRoomId(roomId);
       
       if(lastestBookingModelByRoomId == null) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }

       boolean available = roomService.isThisRoomAvailable(lastestBookingModelByRoomId);
       if(available) {
        customResponse.setMessage("DISPONIVEL/" + lastestBookingModelByRoomId.getCheckOut());
       }else {
        customResponse.setMessage("INDISPONIVEL/" + lastestBookingModelByRoomId.getCheckOut());
       }
       return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }

    //Método para cadastrar um novo quarto
    @PostMapping("/newRoom")
    @ApiOperation("Operação que consiste em cadastrar um novo quarto")
    public ResponseEntity<RoomSchema> createRoom(@RequestBody RoomDTO room) {
        LOGGER.info("Received new room request: {}", room);

        RoomSchema newRoom = new RoomSchema(room);
        roomService.saveNewRoom(newRoom);
        return new ResponseEntity<>(newRoom, HttpStatus.CREATED);
    }

    //Método que lista todos os quartos disponíveis
    @ApiOperation("Operação que consiste em consultar todos os quartos disponiveis")
    @GetMapping("availableRooms")
    public ResponseEntity<List<RoomSchema>> availableRooms() {
        List<RoomSchema> allAvailableRooms = roomService.getAllAvailableRooms();
        return new ResponseEntity<>(allAvailableRooms, HttpStatus.OK);
    }
    
    
}
