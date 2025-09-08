package com.example.DisneyBookingBackend.models.dto;

public class HotelNameDto {
    private Integer hotelId;
    private String hotelName;

    public HotelNameDto() {
    }

    public HotelNameDto(Integer hotelId, String hotelName) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
}
