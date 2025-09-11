package com.example.DisneyBookingBackend.models.dto;

import java.util.Objects;

public class FootprintDto {
    private String city;
    private String date;

    public FootprintDto(String city, String date) {
        this.city = city;
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
        FootprintDto that = (FootprintDto) o;
        return Objects.equals(city, that.city) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, date);
    }
}
