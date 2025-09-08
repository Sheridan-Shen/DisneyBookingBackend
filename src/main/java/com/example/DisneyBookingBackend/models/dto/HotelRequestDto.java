package com.example.DisneyBookingBackend.models.dto;


import java.util.List;

public class HotelRequestDto {
    private String address;
    private List<String> themes;

    public HotelRequestDto() {
    }

    public HotelRequestDto(String address, List<String> themes) {
        this.address = address;
        this.themes = themes;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getThemes() {
        return themes;
    }

    public void setThemes(List<String> themes) {
        this.themes = themes;
    }
}
