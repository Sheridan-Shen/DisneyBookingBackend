package com.example.DisneyBookingBackend.models.mapper;

import com.example.DisneyBookingBackend.models.User;
import com.example.DisneyBookingBackend.models.dto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    
    public UserResponseDto toUserResponseDto(User user) {
        if (user == null) {
            return null;
        }
        return new UserResponseDto(
            user.getUserName(),
            user.getPhone(),
            user.getEmail()
        );
    }
}
