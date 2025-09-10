package com.example.DisneyBookingBackend.controller;

import com.example.DisneyBookingBackend.models.Comment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = "*")
public class CommentController {
    @GetMapping
    public ResponseEntity<List<Comment>> getCommentsByHotelIdAndThemeName(@RequestParam Integer hotelId, @RequestParam String themeName) {

        return ResponseEntity.ok().body(null);
    }
}
