package com.example.DisneyBookingBackend.repository.theme;

import com.example.DisneyBookingBackend.models.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaThemeRepository extends JpaRepository<Theme, Integer> {
}

