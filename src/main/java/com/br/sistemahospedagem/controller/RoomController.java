package com.br.sistemahospedagem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.br.sistemahospedagem.domain.booking.Booking;
import com.br.sistemahospedagem.repositories.BookingRepository;
import com.br.sistemahospedagem.service.RoomService;

@RestController
@RequestMapping("/rooms")
public class RoomController {
     
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    RoomService roomService;
    
    @GetMapping
    public ResponseEntity<CustomResponse> isThisRoomAvailable(@RequestParam("room_id")int roomId) {
        CustomResponse customResponse = new CustomResponse();
        Booking lastestBookingByRoomId = bookingRepository.findLatestBookingByRoomId(roomId);
       
       if(lastestBookingByRoomId == null) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }

       boolean available = roomService.checkAvailability(lastestBookingByRoomId);
       if(available) {
        customResponse.setMessage("DISPONIVEL/" + lastestBookingByRoomId.getCheckOut());
       }else {
        customResponse.setMessage("INDISPONIVEL/" + lastestBookingByRoomId.getCheckOut());
       }
       return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }
    
    
}
