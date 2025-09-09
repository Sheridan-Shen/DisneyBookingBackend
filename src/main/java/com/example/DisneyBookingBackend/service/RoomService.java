package com.example.DisneyBookingBackend.service;

import com.example.DisneyBookingBackend.models.Room;
import com.example.DisneyBookingBackend.models.Theme;
import com.example.DisneyBookingBackend.repository.room.RoomDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    @Autowired
    private RoomDBRepository roomDBRepository;

    @Autowired
    private ThemeService themeService;

    @Autowired
    private HotelService hotelService;

    public Room addRoom(Room room) {
        Theme getTheme = room.getTheme();
        Integer hotelId = room.getHotelId();
        if (getTheme != null) {
            Theme existingTheme = themeService.getThemeById(getTheme.getId());
            if (existingTheme != null) {
                room.setTheme(existingTheme);
            } else {
                throw new IllegalArgumentException("Theme with ID " + getTheme.getId() + " does not exist.");
            }
        }

        if(hotelId == null) {
            throw new IllegalArgumentException("Hotel ID cannot be null.");
        } else  {
            if(hotelService.getAllHotels().stream().noneMatch(hotel -> hotel.getId().equals(hotelId))) {
                throw new IllegalArgumentException("Hotel with ID " + hotelId + " does not exist.");
            }
        }
        return roomDBRepository.save(room);
    }

}

