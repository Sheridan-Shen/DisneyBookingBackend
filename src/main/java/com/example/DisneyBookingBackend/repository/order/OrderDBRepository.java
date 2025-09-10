package com.example.DisneyBookingBackend.repository.order;

import com.example.DisneyBookingBackend.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDBRepository {
    @Autowired
    private JpaOrderRepository jpaOrderRepository;

    public List<Comment> getCommentsByHotelIdAndThemeName(Integer hotelId, String themeName) {
        return jpaOrderRepository.getCommentsByHotelIdAndThemeName(hotelId, themeName);
    }
}
