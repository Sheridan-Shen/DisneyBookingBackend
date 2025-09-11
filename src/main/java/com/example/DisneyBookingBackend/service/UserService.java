package com.example.DisneyBookingBackend.service;

import com.example.DisneyBookingBackend.models.Hotel;
import com.example.DisneyBookingBackend.models.Order;
import com.example.DisneyBookingBackend.models.User;
import com.example.DisneyBookingBackend.models.dto.FootprintDto;
import com.example.DisneyBookingBackend.models.dto.UserResponseDto;
import com.example.DisneyBookingBackend.models.mapper.UserMapper;
import com.example.DisneyBookingBackend.repository.hotel.HotelDBRepository;
import com.example.DisneyBookingBackend.repository.order.OrderDBRepository;
import com.example.DisneyBookingBackend.repository.user.UserDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        return orders.stream()
                .map(order -> {
                    Hotel hotel = hotelDBRepository.getHotelById(order.getHotelId());
                    return new FootprintDto(hotel.getAddress(), order.getCheckIn().format(formatter));
                })
                .distinct()
                .collect(Collectors.toList());
    }
}
