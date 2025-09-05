package com.example.DisneyBookingBackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {
    @GetMapping()
    public String getHotels() {
        return "List of hotels";
    }
}
