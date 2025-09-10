package com.example.DisneyBookingBackend.service;

import com.example.DisneyBookingBackend.models.Order;
import com.example.DisneyBookingBackend.models.Room;
import com.example.DisneyBookingBackend.models.Status;
import com.example.DisneyBookingBackend.repository.order.OrderDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;

@Service
public class OrderService {
    @Autowired
    private OrderDBRepository orderDBRepository;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private ThemeService themeService;

    public HashSet<Integer> selectRoomIdsBetweenCheckInAndCheckOut(Integer hotelId, LocalDate checkIn, LocalDate checkOut) {
        return new HashSet<>();
    }

    public void createOrder(Order order) {
        order.setHotelName(hotelService.getHotelNameById(order.getHotelId()));
        order.setThemeId(themeService.getThemeIdByName(order.getThemeName()));

        // Todo 在room表中根据hotelId和themes查找可用的房间
        order.setRoomIds("1,2");
        order.setRoomNumbers("401,402");
        order.setRoomName("Frozen Magic 主题房");



        // Todo 去数据库请求User信息
        order.setUserName("Li Hua");
        order.setPhone("13800138000");

        order.setStatus(Status.CONFIRMED);

        orderDBRepository.createOrder(order);
    }
}

