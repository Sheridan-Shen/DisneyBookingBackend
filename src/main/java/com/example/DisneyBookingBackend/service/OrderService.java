package com.example.DisneyBookingBackend.service;

import com.example.DisneyBookingBackend.models.Room;
import com.example.DisneyBookingBackend.repository.order.OrderDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;

@Service
public class OrderService {
    @Autowired
    private OrderDBRepository orderDBRepository;

    public HashSet<Integer> selectRoomIdsBetweenCheckInAndCheckOut(Integer hotelId, LocalDate checkIn, LocalDate checkOut) {
        return new HashSet<>();
    }
}

