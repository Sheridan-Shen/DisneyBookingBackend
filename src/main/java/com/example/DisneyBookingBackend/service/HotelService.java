package com.example.DisneyBookingBackend.service;

import com.example.DisneyBookingBackend.models.Hotel;
import com.example.DisneyBookingBackend.models.Theme;
import com.example.DisneyBookingBackend.models.dto.HotelNameDto;
import com.example.DisneyBookingBackend.models.dto.HotelRequestDto;
import com.example.DisneyBookingBackend.repository.hotel.HotelDBRepository;
import com.example.DisneyBookingBackend.repository.theme.ThemeDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService {
    @Autowired
    private HotelDBRepository hotelDBRepository;
    @Autowired
    private ThemeDBRepository themeDBRepository;

    public List<Hotel> getAllHotels() {
        return hotelDBRepository.getAllHotels();
    }

    public List<HotelNameDto> getAllHotelNames() {
        return hotelDBRepository.getAllHotelsNames();
    }

    public Hotel addHotel(Hotel hotel) {
        List<Theme> inputThemes = hotel.getThemes();
        List<Theme> persistedThemes = new ArrayList<>();

        for (Theme theme : inputThemes) {
            Theme existing = themeDBRepository.findByThemeName(theme.getThemeName());
            if (existing != null) {
                persistedThemes.add(existing);
            } else {
                Theme saved = themeDBRepository.save(new Theme(theme.getThemeName()));
                persistedThemes.add(saved);
            }
        }
        hotel.setThemes(persistedThemes);
        return hotelDBRepository.save(hotel);
    }

    public List<Hotel> getHotelsByAddressAndThemes(HotelRequestDto hotelRequestDto) {
        if (hotelRequestDto.getAddress() == null && hotelRequestDto.getThemes().isEmpty()) {
            return hotelDBRepository.getAllHotels();
        }

        if (hotelRequestDto.getAddress() != null && hotelRequestDto.getThemes().isEmpty()) {
            List<String> allThemeNames = themeDBRepository.getAllThemeNames();
            return hotelDBRepository.findHotelsByAddressAndThemes(
                    hotelRequestDto.getAddress(),
                    allThemeNames
            );
        }

        if (hotelRequestDto.getAddress() == null && !hotelRequestDto.getThemes().isEmpty()) {
            return hotelDBRepository.findHotelsByThemeNames(hotelRequestDto.getThemes());
        }

        return hotelDBRepository.findHotelsByAddressAndThemes(
                hotelRequestDto.getAddress(),
                hotelRequestDto.getThemes()
        );
    }

    public List<String> getAllHotelCities() {
        return hotelDBRepository.getAllHotelCities();
    }

    public String getHotelNameById(Integer hotelId) {
        return hotelDBRepository.getHotelNameById(hotelId);
    }
}
