package com.example.DisneyBookingBackend.service;

import com.example.DisneyBookingBackend.models.Hotel;
import com.example.DisneyBookingBackend.models.Order;
import com.example.DisneyBookingBackend.models.User;
import com.example.DisneyBookingBackend.models.dto.AchievementDto;
import com.example.DisneyBookingBackend.models.dto.FootprintDto;
import com.example.DisneyBookingBackend.models.dto.UserResponseDto;
import com.example.DisneyBookingBackend.models.mapper.UserMapper;
import com.example.DisneyBookingBackend.repository.hotel.HotelDBRepository;
import com.example.DisneyBookingBackend.repository.order.OrderDBRepository;
import com.example.DisneyBookingBackend.repository.user.UserDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.minBy;

@Service
public class UserService {
    
    @Autowired
    private UserDBRepository userDBRepository;

    @Autowired
    private OrderDBRepository orderDBRepository;

    @Autowired
    private HotelDBRepository hotelDBRepository;
    
    @Autowired
    private UserMapper userMapper;
    
    public UserResponseDto getUserById(Integer userId) {
        Optional<User> userOptional = userDBRepository.getUserById(userId);
        return userOptional.map(user -> userMapper.toUserResponseDto(user)).orElse(null);
    }

    public boolean changePassword(Integer userId, String oldPassword, String newPassword) {
        Optional<User> userOptional = userDBRepository.getUserById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(oldPassword)) {
                user.setPassword(newPassword);
                userDBRepository.save(user);
                return true;
            }
        }
        return false;
    }

    public List<FootprintDto> getFootprints(Integer userId) {
        List<Order> orders = orderDBRepository.getOrdersByUserId(userId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM").withZone(ZoneId.of("Asia/Shanghai"));

        return orders.stream()
                .collect(Collectors.toMap(order -> hotelDBRepository.getHotelById(order.getHotelId()).getAddress(), Function.identity(), BinaryOperator.minBy(Comparator.comparing(Order::getOrderDate))))
                .values().stream()
                .map(order -> new FootprintDto(hotelDBRepository.getHotelById(order.getHotelId()).getAddress(), formatter.format(order.getOrderDate())))
                .collect(Collectors.toList());
    }

    public List<AchievementDto> getAchievements(Integer userId) {
        List<Order> orders = orderDBRepository.getOrdersByUserId(userId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM").withZone(ZoneId.of("Asia/Shanghai"));

        return orders.stream()
                .collect(Collectors.toMap(Order::getThemeName, Function.identity(), BinaryOperator.minBy(Comparator.comparing(Order::getOrderDate))))
                .values().stream()
                .map(order -> new AchievementDto(order.getThemeName(), formatter.format(order.getOrderDate())))
                .collect(Collectors.toList());
    }

    public String getUserNameById(Integer userId) {
        Optional<User> userOptional = userDBRepository.getUserById(userId);
        return userOptional.map(User::getUserName).orElse(null);
    }

    public String getPhoneById(Integer userId) {
Optional<User> userOptional = userDBRepository.getUserById(userId);
        return userOptional.map(User::getPhone).orElse(null);
    }
}
