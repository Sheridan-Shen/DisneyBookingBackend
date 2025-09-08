package com.example.DisneyBookingBackend.controller;

import com.example.DisneyBookingBackend.models.Hotel;
import com.example.DisneyBookingBackend.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@CrossOrigin(origins = "*")
public class HotelController {
    @Autowired
    HotelService hotelService;

    @GetMapping()
    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @GetMapping("/names")
    public List<String> getHotels() {
        return hotelService.getAllHotelNames();
    }
}
