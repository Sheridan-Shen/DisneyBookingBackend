package com.example.DisneyBookingBackend.repository.theme;

import com.example.DisneyBookingBackend.models.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaThemeRepository extends JpaRepository<Theme, Integer> {
    @Query("SELECT t.themeName FROM Theme t")
    public List<String> getAllThemeNames();
}

