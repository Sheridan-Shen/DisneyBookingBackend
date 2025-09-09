package com.example.DisneyBookingBackend.service;

import com.example.DisneyBookingBackend.models.Theme;
import com.example.DisneyBookingBackend.repository.theme.ThemeDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ThemeService {
    @Autowired
    private ThemeDBRepository themeDBRepository;

    public Theme getThemeByName(String themeName) {
        return themeDBRepository.findByThemeName(themeName);
    }

    public Theme save(Theme theme) {
        return themeDBRepository.save(theme);
    }

    public Theme getThemeById(Integer id) {
        return themeDBRepository.findById(id);
    }
}

