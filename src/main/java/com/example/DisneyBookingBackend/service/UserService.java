package com.example.DisneyBookingBackend.service;

import com.example.DisneyBookingBackend.models.User;
import com.example.DisneyBookingBackend.models.dto.UserResponseDto;
import com.example.DisneyBookingBackend.models.mapper.UserMapper;
import com.example.DisneyBookingBackend.repository.user.UserDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    private UserDBRepository userDBRepository;
    
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
}
