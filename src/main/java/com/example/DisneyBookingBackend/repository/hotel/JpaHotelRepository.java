package com.example.DisneyBookingBackend.repository.hotel;

import com.example.DisneyBookingBackend.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaHotelRepository extends JpaRepository<Hotel, Integer> {
    @Query("SELECT h.hotelName FROM Hotel h")
    List<String> getAllHotelNames();
}
