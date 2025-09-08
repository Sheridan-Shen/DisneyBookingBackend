package com.example.DisneyBookingBackend.service;

import com.example.DisneyBookingBackend.models.Hotel;
import com.example.DisneyBookingBackend.models.dto.HotelNameDto;
import com.example.DisneyBookingBackend.repository.hotel.HotelDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    @Autowired
    private HotelDBRepository hotelDBRepository;

    public List<Hotel> getAllHotels() {
        return hotelDBRepository.getAllHotels();
    }

    public List<HotelNameDto> getAllHotelNames() {
        return hotelDBRepository.getAllHotelsNames();
    }
}
