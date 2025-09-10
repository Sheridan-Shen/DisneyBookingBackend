package com.example.DisneyBookingBackend.models.mapper;

import com.example.DisneyBookingBackend.models.Order;
import com.example.DisneyBookingBackend.models.dto.OrderRequestDto;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public Order toOrder(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        order.setUserId(orderRequestDto.getUserId());
        order.setHotelId(orderRequestDto.getHotelId());
        order.setThemeName(orderRequestDto.getThemeName());
        order.setRoomCount(orderRequestDto.getRoomCount());
        order.setCheckIn(orderRequestDto.getCheckIn());
        order.setCheckOut(orderRequestDto.getCheckOut());
        order.setTotalPrice(orderRequestDto.getTotalPrice());
        order.setOrderRemark(orderRequestDto.getOrderRemark());
        return order;
    }
}
