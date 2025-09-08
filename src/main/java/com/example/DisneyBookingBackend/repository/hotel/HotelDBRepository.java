package com.example.DisneyBookingBackend.repository.hotel;

import com.example.DisneyBookingBackend.models.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HotelDBRepository {
    @Autowired
    private JpaHotelRepository jpaHotelRepository;

    public List<Hotel> getAllHotels() {
        return jpaHotelRepository.findAll();
    }

    public List<String> getAllHotelsNames() {
        return jpaHotelRepository.getAllHotelNames();
    }
}
