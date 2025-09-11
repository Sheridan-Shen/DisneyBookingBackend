package com.example.DisneyBookingBackend.repository.room;

import com.example.DisneyBookingBackend.models.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class RoomDBRepository {
    @Autowired
    private JpaRoomRepository jpaRoomRepository;

    public Room save(Room room) {
        return jpaRoomRepository.save(room);
    }

    public List<Room> getRoomsByHotelId(Integer hotelId) {
        return jpaRoomRepository.findByHotelIdAndIsDeletedFalse(hotelId);
    }

    public List<Room> getAllRooms() {
        return jpaRoomRepository.findAll();
    }

    public List<Integer> getAllRoomIdsByHotelIdAndThemeId(Integer hotelId, Integer themeId) {
        return jpaRoomRepository.getAllRoomIdsByHotelIdAndThemeId(hotelId, themeId);
    }

    public List<Integer> getRoomNumbersByRoomIds(List<Integer> roomIds) {
        return jpaRoomRepository.getRoomNumbersByRoomIds(roomIds);
    }

    public String selectRoomNameByRoomId(Integer roomId) {
        return jpaRoomRepository.selectRoomNameByRoomId(roomId);
    }

    public List<BigDecimal> selectRoomPricesByHotelId(Integer hotelId) {
        return jpaRoomRepository.findPricesByHotelId(hotelId);
    }
}

