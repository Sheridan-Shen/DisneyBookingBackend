package com.example.DisneyBookingBackend.models;

import java.time.Instant;

public class Comment {
    private String userName;
    private String comment;
    private Float rating;
    private Instant ratingDate;

    public Comment() {
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
