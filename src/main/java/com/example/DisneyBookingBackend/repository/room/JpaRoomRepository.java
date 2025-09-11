package com.example.DisneyBookingBackend.repository.room;

import com.example.DisneyBookingBackend.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface JpaRoomRepository extends JpaRepository<Room, Integer> {
    //    @Query("SELECT r FROM Room r WHERE r.hotel_id = ?1 AND r.isDeleted = false")
    @NativeQuery("SELECT * FROM room WHERE hotel_id = ?1 AND is_deleted = false")
    List<Room> findByHotelIdAndIsDeletedFalse(Integer hotelId);

    @Query("SELECT r.roomId FROM Room r WHERE r.hotelId = :hotelId AND r.theme.id = :themeId AND r.isDeleted = false")
    List<Integer> getAllRoomIdsByHotelIdAndThemeId(
            @Param("hotelId") Integer hotelId,
            @Param("themeId") Integer themeId);

    @Query("SELECT r.roomNumber FROM Room r WHERE r.roomId IN :roomIds AND r.isDeleted = false")
    List<Integer> getRoomNumbersByRoomIds(@Param("roomIds") List<Integer> roomIds);

    @Query("SELECT r.roomName FROM Room r WHERE r.roomId = :roomId AND r.isDeleted = false")
    String selectRoomNameByRoomId(@Param("roomId") Integer roomId);

    @Query("SELECT r.price FROM Room r WHERE r.hotelId = :hotelId AND r.isDeleted = false")
    List<BigDecimal> findPricesByHotelId(@Param("hotelId") Integer hotelId);
}

