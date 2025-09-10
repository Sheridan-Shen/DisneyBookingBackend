package com.example.DisneyBookingBackend.repository.hotel;

import com.example.DisneyBookingBackend.models.Hotel;
import com.example.DisneyBookingBackend.models.dto.HotelNameDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaHotelRepository extends JpaRepository<Hotel, Integer> {
    @Query("SELECT new com.example.DisneyBookingBackend.models.dto.HotelNameDto(h.id, h.hotelName) FROM Hotel h")
    List<HotelNameDto> getAllHotelNames();

    @Query("""
        SELECT DISTINCT h FROM Hotel h
        JOIN h.themes t
        WHERE h.address LIKE %:address%
          AND t.themeName IN :themeNames
          AND h.isDeleted = false
        """)
    List<Hotel> findHotelsByCityAndThemes(String address, List<String> themeNames);

    @Query("SELECT DISTINCT h FROM Hotel h JOIN h.themes t WHERE t.themeName IN :themeNames")
    List<Hotel> findHotelsByThemeNames(List<String> themeNames);

    @Query("SELECT DISTINCT h.address FROM Hotel h WHERE h.isDeleted = false")
    List<String> getAllHotelCities();

    @Query("SELECT DISTINCT h.hotelName FROM Hotel h WHERE h.id = :hotelId")
    String getHotelNameById(Integer hotelId);
}
