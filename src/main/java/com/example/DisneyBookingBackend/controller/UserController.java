package com.example.DisneyBookingBackend.controller;

import com.example.DisneyBookingBackend.models.Order;
import com.example.DisneyBookingBackend.models.dto.OrderResponseDto;
import com.example.DisneyBookingBackend.models.mapper.OrderMapper;
import com.example.DisneyBookingBackend.repository.order.OrderDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private OrderDBRepository orderDBRepository;

    @Autowired
    private OrderMapper orderMapper;

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<OrderResponseDto>> getUserOrders(@PathVariable Integer userId) {
        List<Order> orders = orderDBRepository.getOrdersByUserId(userId);
        List<OrderResponseDto> orderResponseDtos = orderMapper.toOrderResponseDtoList(orders);
        return ResponseEntity.ok(orderResponseDtos);
    }
}
