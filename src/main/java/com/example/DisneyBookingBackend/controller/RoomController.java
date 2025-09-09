package com.example.DisneyBookingBackend.controller;

import com.example.DisneyBookingBackend.models.Room;
import com.example.DisneyBookingBackend.models.dto.AvailableThemeRoomDto;
import com.example.DisneyBookingBackend.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "*")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @PostMapping("/add")
    public Room addRoom(@RequestBody Room room) {
        return roomService.addRoom(room);
    }

    @GetMapping
    public ResponseEntity<List<AvailableThemeRoomDto>> getAvailableRooms(
            @RequestParam Integer hotelId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut,
            @RequestParam(defaultValue = "1") Integer availableRoomNumber) {
        return ResponseEntity.ok(roomService.getAvailableRooms(hotelId, checkIn, checkOut, availableRoomNumber));
    }
}
