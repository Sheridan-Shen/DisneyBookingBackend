package com.example.DisneyBookingBackend.controller;

import com.example.DisneyBookingBackend.models.Order;
import com.example.DisneyBookingBackend.models.dto.CommentRequestDto;
import com.example.DisneyBookingBackend.models.dto.OrderRequestDto;
import com.example.DisneyBookingBackend.models.mapper.OrderMapper;
import com.example.DisneyBookingBackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper orderMapper;

    @PostMapping()
    public ResponseEntity<String> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        Order order = orderMapper.toOrder(orderRequestDto);
        orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body("Order created successfully");
    }

    @PostMapping("/{orderId}/comments")
    public ResponseEntity<?> addComment(
            @PathVariable Integer orderId,
            @RequestBody CommentRequestDto request) {

        orderService.addComment(orderId, request.getRating(), request.getComment());
        return ResponseEntity.ok().body("Comment added successfully");
    }
}
