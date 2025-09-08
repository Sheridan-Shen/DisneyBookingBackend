package com.example.DisneyBookingBackend.models.dto;

import com.example.DisneyBookingBackend.models.Theme;

import java.util.ArrayList;
import java.util.List;

public class HotelResponseDto {
    private int hotelId;
    private String hotelName;
    private String address;
    private String description;
    private Float ratings;
    private List<Theme> themes = new ArrayList<>();

    private Boolean success;

    public HotelResponseDto() {
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getRatings() {
        return ratings;
    }

    public void setRatings(Float ratings) {
        this.ratings = ratings;
    }

    public List<Theme> getThemes() {
        return themes;
    }

    public void setThemes(List<Theme> themes) {
        this.themes = themes;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
