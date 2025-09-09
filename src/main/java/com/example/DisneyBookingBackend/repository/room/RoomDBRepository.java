package com.example.DisneyBookingBackend.repository.room;

import com.example.DisneyBookingBackend.models.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}

