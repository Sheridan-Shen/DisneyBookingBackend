package com.example.DisneyBookingBackend.service;

import com.example.DisneyBookingBackend.models.Hotel;
import com.example.DisneyBookingBackend.models.Room;
import com.example.DisneyBookingBackend.models.Theme;
import com.example.DisneyBookingBackend.models.dto.AvailableThemeRoomDto;
import com.example.DisneyBookingBackend.models.dto.SampleRoomDto;
import com.example.DisneyBookingBackend.models.mapper.RoomMapper;
import com.example.DisneyBookingBackend.repository.order.OrderDBRepository;
import com.example.DisneyBookingBackend.repository.room.RoomDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoomService {
    @Autowired
    private RoomDBRepository roomDBRepository;

    @Autowired
    private OrderDBRepository orderDBRepository;

    @Autowired
    private ThemeService themeService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private RoomMapper roomMapper;

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

        if (hotelId == null) {
            throw new IllegalArgumentException("Hotel ID cannot be null.");
        } else {
            if (hotelService.getAllHotels().stream().noneMatch(hotel -> hotel.getId().equals(hotelId))) {
                throw new IllegalArgumentException("Hotel with ID " + hotelId + " does not exist.");
            }
        }
        return roomDBRepository.save(room);
    }

    public List<AvailableThemeRoomDto> getAvailableRooms(Integer hotelId, LocalDate checkIn, LocalDate checkOut, Integer availableRoomNumber) {
        if (hotelId == null) {
            throw new IllegalArgumentException("Hotel ID cannot be null.");
        }

        if (checkIn == null || checkOut == null) {
            throw new IllegalArgumentException("Check-in and check-out dates cannot be null.");
        }

        if (checkIn.isAfter(checkOut) || checkIn.isEqual(checkOut)) {
            throw new IllegalArgumentException("Check-in date must be before check-out date.");
        }

        if (availableRoomNumber == null || availableRoomNumber <= 0) {
            throw new IllegalArgumentException("Available room number must be a positive integer.");
        }

        List<Room> targetHotelIdRooms = roomDBRepository.getRoomsByHotelId(hotelId);

        HashSet<Integer> occupiedRoomIdSet = orderService.selectRoomIdsBetweenCheckInAndCheckOut(hotelId, checkIn, checkOut);

        List<Room> availableRooms = targetHotelIdRooms.stream()
                .filter(room -> !occupiedRoomIdSet.contains(room.getRoomId()))
                .collect(Collectors.toList());

        return getAvailableRoomsGroupByTheme(availableRooms, availableRoomNumber);
    }

    public List<Room> getRoomsByHotelId(Integer hotelId) {
        return roomDBRepository.getRoomsByHotelId(hotelId);
    }

    public List<Room> getAllRooms() {
        return roomDBRepository.getAllRooms();
    }

    public List<AvailableThemeRoomDto> getAvailableRoomsGroupByTheme(List<Room> availableRooms, Integer availableRoomNumber) {
        Map<Theme, List<Room>> groupedByTheme = availableRooms.stream()
                .collect(Collectors.groupingBy(Room::getTheme));

        List<AvailableThemeRoomDto> result = new ArrayList<>();
        for (Map.Entry<Theme, List<Room>> entry : groupedByTheme.entrySet()) {
            Theme theme = entry.getKey();
            List<Room> rooms = entry.getValue();

            if (rooms.size() < availableRoomNumber) {
                continue;
            }

            SampleRoomDto sampleRoomDto = roomMapper.toSampleRoomDto(rooms.get(0));

            List<Float> ratings = orderDBRepository.selectRatingsByHotelIdAndThemeId(rooms.get(0).getHotelId(), theme.getId());
            float averageRating = 4.0F;
            if (!ratings.isEmpty()) {
                float sum = 0.0F;
                for (Float rating : ratings) {
                    sum += rating;
                }
                averageRating = sum / ratings.size();
            }
            averageRating = Math.round(averageRating * 10) / 10.0F;

            AvailableThemeRoomDto dto = new AvailableThemeRoomDto(theme.getThemeName(), rooms.size(), averageRating, sampleRoomDto);
            result.add(dto);
        }
        return result;
    }
}

