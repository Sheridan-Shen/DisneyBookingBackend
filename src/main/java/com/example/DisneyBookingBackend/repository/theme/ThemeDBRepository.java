package com.example.DisneyBookingBackend.repository.theme;

import com.example.DisneyBookingBackend.models.Theme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ThemeDBRepository {

    @Autowired
    private JpaThemeRepository jpaThemeRepository;
    public Theme findByThemeName(String themeName) {
        List<Theme> themes = jpaThemeRepository.findAll();
        for (Theme theme : themes) {
            if (theme.getThemeName().equalsIgnoreCase(themeName)) {
                return theme;
            }
        }
        return null;
    }

    public Theme save(Theme theme) {
        return jpaThemeRepository.save(theme);
    }

    public Theme findById(Integer id) {
        return jpaThemeRepository.findById(id).orElse(null);
    }

    public List<String> getAllThemeNames() {
        return jpaThemeRepository.getAllThemeNames();
    }
}

