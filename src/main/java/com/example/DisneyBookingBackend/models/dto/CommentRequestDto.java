package com.example.DisneyBookingBackend.models.dto;

public class CommentRequestDto {
    private Float rating;
    private String comment;

    public CommentRequestDto() {
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
