package com.example.DisneyBookingBackend.models.dto;

import java.math.BigDecimal;
import java.util.List;

public class SampleRoomDto {
    private Integer roomId;
    private String roomName;
    private BigDecimal price;
    private String imageUrls;
    private String description;
    private Float rating;

    public SampleRoomDto() {
    }

    public SampleRoomDto(Integer roomId, String roomName, BigDecimal price, String imageUrls, String description, Float rating) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.price = price;
        this.imageUrls = imageUrls;
        this.description = description;
        this.rating = rating;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(String imageUrls) {
        this.imageUrls = imageUrls;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}
