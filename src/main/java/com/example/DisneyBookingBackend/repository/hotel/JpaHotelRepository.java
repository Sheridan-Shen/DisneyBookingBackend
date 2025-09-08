package com.example.DisneyBookingBackend.repository.hotel;

import com.example.DisneyBookingBackend.models.Hotel;
import com.example.DisneyBookingBackend.models.dto.HotelNameDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaHotelRepository extends JpaRepository<Hotel, Integer> {
    @Query("SELECT new com.example.DisneyBookingBackend.models.dto.HotelNameDto(h.id, h.hotelName) FROM Hotel h")
    List<HotelNameDto> getAllHotelNames();
}
