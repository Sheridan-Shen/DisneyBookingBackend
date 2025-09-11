package com.example.DisneyBookingBackend.repository.user;

import com.example.DisneyBookingBackend.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDBRepository {
    
    @Autowired
    private JpaUserRepository jpaUserRepository;
    
    public Optional<User> getUserById(Integer userId) {
        return jpaUserRepository.findById(userId);
    }
}

