package com.example.DisneyBookingBackend.repository.user;

import com.example.DisneyBookingBackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<User, Integer> {
}
