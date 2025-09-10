package com.example.DisneyBookingBackend.repository.order;

import com.example.DisneyBookingBackend.models.Comment;
import com.example.DisneyBookingBackend.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
}
