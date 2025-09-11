package com.example.DisneyBookingBackend.repository.order;

import com.example.DisneyBookingBackend.models.Comment;
import com.example.DisneyBookingBackend.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface JpaOrderRepository extends JpaRepository<Order, Integer> {

    @Query("""
            SELECT NEW com.example.DisneyBookingBackend.models.Comment(
                o.userName,
                o.comment,
                o.rating,
                o.ratingDate
            )
            FROM Order o
            WHERE o.hotelId = :hotelId
              AND o.themeName = :themeName
              AND o.isDeleted = false
            """)
    List<Comment> getCommentsByHotelIdAndThemeName(
            @Param("hotelId") Integer hotelId,
            @Param("themeName") String themeName);

    /**
     * 查询在指定日期范围内、指定酒店的所有订单的 room_ids 字符串
     * 日期重叠条件：订单的 checkIn < 传入的 checkOut && 订单的 checkOut > 传入的 checkIn
     */
    @Query(value = """
            SELECT room_ids
            FROM booking_order
            WHERE hotel_id = :hotelId
              AND check_in < :checkOut
              AND check_out > :checkIn
              AND status != 'CANCELLED'
              AND status != 'COMPLETED'
              AND is_deleted = false
            """, nativeQuery = true)
    List<String> selectRoomIdsBetweenCheckInAndCheckOut(
            @Param("hotelId") Integer hotelId,
            @Param("checkIn") LocalDate checkIn,
            @Param("checkOut") LocalDate checkOut);

    List<Order> findByUserIdAndIsDeletedFalse(Integer userId);

    @Query("SELECT o.rating FROM Order o " +
            "WHERE o.hotelId = :hotelId " +
            "  AND o.themeId = :themeId " +
            "  AND o.isDeleted = false " +
            "  AND o.rating IS NOT NULL")
    List<Float> selectRatingsByHotelIdAndThemeId(
            @Param("hotelId") Integer hotelId,
            @Param("themeId") Integer themeId);

    @Query("SELECT o.rating FROM Order o WHERE o.hotelId = :hotelId AND o.rating IS NOT NULL AND o.isDeleted = false")
    List<Float> selectRatingsByHotelId(@Param("hotelId") Integer hotelId);
}
