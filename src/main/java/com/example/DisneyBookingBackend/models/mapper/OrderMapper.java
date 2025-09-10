package com.example.DisneyBookingBackend.models.mapper;

import com.example.DisneyBookingBackend.models.Order;
import com.example.DisneyBookingBackend.models.dto.OrderRequestDto;
import com.example.DisneyBookingBackend.models.dto.OrderResponseDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    public OrderResponseDto toOrderResponseDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        BeanUtils.copyProperties(order, orderResponseDto);
        return orderResponseDto;
    }

    public List<OrderResponseDto> toOrderResponseDtoList(List<Order> orders) {
        return orders.stream()
                .map(this::toOrderResponseDto)
                .collect(Collectors.toList());
    }
}
