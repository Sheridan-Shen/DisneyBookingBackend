package com.example.DisneyBookingBackend.models.dto;

import com.example.DisneyBookingBackend.models.Theme;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class HotelResponseDto {
    private int id;
    private String name;
    private String address;
    private String description;
    private Float rating;
    private BigDecimal minimumPrice = BigDecimal.valueOf(1314);
    private String images;
    private List<Theme> themes = new ArrayList<>();

    public HotelResponseDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public List<Theme> getThemes() {
        return themes;
    }

    public void setThemes(List<Theme> themes) {
        this.themes = themes;
    }

    public BigDecimal getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(BigDecimal minimumPrice) {
        this.minimumPrice = minimumPrice;
    }

    public String getImages() {
        return images;
    }
    public void setImages(String images) {
        this.images = images;
    }
}
