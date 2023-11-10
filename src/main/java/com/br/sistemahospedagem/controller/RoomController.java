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

@RestController
@RequestMapping("/rooms")
public class RoomController {
     
    @Autowired
    BookingRepository bookingRepository;
    
    @GetMapping
    public ResponseEntity<Booking> isThisRoomBusy(@RequestParam("room_id")int roomId) {
       Booking lastestBookingByRoomId = bookingRepository.findTopByIdOrderByIdDesc(roomId);
       if(lastestBookingByRoomId == null) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
       return new ResponseEntity<>(lastestBookingByRoomId, HttpStatus.OK);
    }
    
}
