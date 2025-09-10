package com.example.DisneyBookingBackend.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public class Comment {
    private String userName;
    private String comment;
    private Float rating;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Instant ratingDate;

    public Comment() {
    }

    public Comment(String userName, String comment, Float rating, Instant ratingDate) {
        this.userName = userName;
        this.comment = comment;
        this.rating = rating;
        this.ratingDate = ratingDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Instant getRatingDate() {
        return ratingDate;
    }

    public void setRatingDate(Instant ratingDate) {
        this.ratingDate = ratingDate;
    }
}
