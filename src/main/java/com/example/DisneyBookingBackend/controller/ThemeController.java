package com.example.DisneyBookingBackend.controller;

import com.example.DisneyBookingBackend.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/themes")
public class ThemeController {
    @Autowired
    private ThemeService themeService;

    @GetMapping("/names")
    public ResponseEntity<List<String>> getAllThemeNames() {
        return ResponseEntity.ok(themeService.getAllThemeNames());
    }
}
