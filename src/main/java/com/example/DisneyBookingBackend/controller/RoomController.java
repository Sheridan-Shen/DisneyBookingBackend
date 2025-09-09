package com.example.DisneyBookingBackend.controller;

import com.example.DisneyBookingBackend.models.Room;
import com.example.DisneyBookingBackend.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "*")
public class RoomController {
    @Autowired
    private RoomService roomCService;


    @PostMapping("/add")
    public Room addRoom(@RequestBody Room room) {
        return roomCService.addRoom(room);
    }
}
