package com.example.DisneyBookingBackend.service;

import com.example.DisneyBookingBackend.models.Comment;
import com.example.DisneyBookingBackend.repository.order.OrderDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private OrderDBRepository orderDBRepository;

    public List<Comment> getCommentsByHotelIdAndThemeName(Integer hotelId, String themeName) {
        List<Comment> commentsByHotelIdAndThemeName = orderDBRepository.getCommentsByHotelIdAndThemeName(hotelId, themeName);
        return commentsByHotelIdAndThemeName;
    }
}
