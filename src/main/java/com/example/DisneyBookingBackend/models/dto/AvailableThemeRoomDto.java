package com.example.DisneyBookingBackend.models.dto;


public class AvailableThemeRoomDto {
    private String themeName;
    private Integer availableRoomCount;
    private Float averageRating;
    private SampleRoomDto sampleRoom;

    public AvailableThemeRoomDto() {
    }

    public AvailableThemeRoomDto(String themeName, Integer availableRoomCount, Float averageRating, SampleRoomDto sampleRoom) {
        this.themeName = themeName;
        this.availableRoomCount = availableRoomCount;
        this.averageRating = averageRating;
        this.sampleRoom = sampleRoom;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public Integer getAvailableRoomCount() {
        return availableRoomCount;
    }

    public void setAvailableRoomCount(Integer availableRoomCount) {
        this.availableRoomCount = availableRoomCount;
    }

    public Float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Float averageRating) {
        this.averageRating = averageRating;
    }

    public SampleRoomDto getSampleRoom() {
        return sampleRoom;
    }

    public void setSampleRoom(SampleRoomDto sampleRoom) {
        this.sampleRoom = sampleRoom;
    }
}