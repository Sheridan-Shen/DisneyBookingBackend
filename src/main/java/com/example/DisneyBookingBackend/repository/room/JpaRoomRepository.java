package com.example.DisneyBookingBackend.repository.room;

import com.example.DisneyBookingBackend.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaRoomRepository extends JpaRepository<Room, Integer> {
//    @Query("SELECT r FROM Room r WHERE r.hotel_id = ?1 AND r.isDeleted = false")
    @NativeQuery("SELECT * FROM room WHERE hotel_id = ?1 AND is_deleted = false")
    List<Room> findByHotelIdAndIsDeletedFalse(Integer hotelId);

    List<Integer> getAllRoomIdsByHotelIdAndThemeId(Integer hotelId, Integer themeId);
}

