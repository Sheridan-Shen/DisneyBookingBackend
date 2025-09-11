package com.example.DisneyBookingBackend.models.dto;

import java.util.Objects;

public class AchievementDto {
    private String theme;
    private String date;

    public AchievementDto(String theme, String date) {
        this.theme = theme;
        this.date = date;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AchievementDto that = (AchievementDto) o;
        return Objects.equals(theme, that.theme) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(theme, date);
    }
}
