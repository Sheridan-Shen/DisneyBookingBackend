package com.example.DisneyBookingBackend.controller;

import com.example.DisneyBookingBackend.models.Hotel;
import com.example.DisneyBookingBackend.models.dto.HotelNameDto;
import com.example.DisneyBookingBackend.models.dto.HotelRequestDto;
import com.example.DisneyBookingBackend.models.dto.HotelResponseDto;
import com.example.DisneyBookingBackend.models.mapper.HotelMapper;
import com.example.DisneyBookingBackend.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@CrossOrigin(origins = "*")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @Autowired
    private HotelMapper hotelMapper;

    @GetMapping()
    public List<HotelResponseDto> getAllHotels() {
        List<Hotel> hotels = hotelService.getAllHotels();
        return hotelMapper.toResponseList(hotels);
    }

    @GetMapping("/names")
    public List<HotelNameDto> getHotels() {
        return hotelService.getAllHotelNames();
    }

    @PostMapping("/search")
    public ResponseEntity<List<HotelResponseDto>> getHotelsByAddressAndThemes(@RequestBody HotelRequestDto hotelRequestDto) {
        List<Hotel> result = hotelService.getHotelsByAddressAndThemes(hotelRequestDto);
        return ResponseEntity.ok(hotelMapper.toResponseList(result));
    }

    @PostMapping("/add")
    public Hotel addHotel(@RequestBody Hotel hotel) {
        return hotelService.addHotel(hotel);
    }

    @GetMapping("/cities")
    public ResponseEntity<List<String>> getAllHotelCities() {
        List<String> cities = hotelService.getAllHotelCities();
        return ResponseEntity.ok(cities);
    }

}
