package com.example.DisneyBookingBackend.controller;

import com.example.DisneyBookingBackend.models.Order;
import com.example.DisneyBookingBackend.models.dto.OrderResponseDto;
import com.example.DisneyBookingBackend.models.dto.PasswordChangeRequestDto;
import com.example.DisneyBookingBackend.models.dto.UserResponseDto;
import com.example.DisneyBookingBackend.models.mapper.OrderMapper;
import com.example.DisneyBookingBackend.repository.order.OrderDBRepository;
import com.example.DisneyBookingBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private OrderDBRepository orderDBRepository;

    @Autowired
    private OrderMapper orderMapper;
    
    @Autowired
    private UserService userService;

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<OrderResponseDto>> getUserOrders(@PathVariable Integer userId) {
        List<Order> orders = orderDBRepository.getOrdersByUserId(userId);
        List<OrderResponseDto> orderResponseDtos = orderMapper.toOrderResponseDtoList(orders);
        return ResponseEntity.ok(orderResponseDtos);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUserInfo(@PathVariable Integer userId) {
        UserResponseDto userResponseDto = userService.getUserById(userId);
        if (userResponseDto != null) {
            return ResponseEntity.ok(userResponseDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{userId}/password")
    public ResponseEntity<Map<String, Object>> changePassword(@PathVariable Integer userId, @RequestBody PasswordChangeRequestDto request) {
        boolean success = userService.changePassword(userId, request.getOldPassword(), request.getNewPassword());
        if (success) {
            return ResponseEntity.ok(Map.of("status", 200, "message", "Password changed successfully", "success", true));
        } else {
            return ResponseEntity.badRequest().body(Map.of("status", 400, "message", "Password change failed", "success", false));
        }
    }
}
