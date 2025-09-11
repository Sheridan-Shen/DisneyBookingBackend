package com.example.DisneyBookingBackend.models.mapper;

import com.example.DisneyBookingBackend.models.Hotel;
import com.example.DisneyBookingBackend.models.dto.HotelResponseDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HotelMapper {
    public HotelResponseDto toResponse(Hotel hotel) {
        HotelResponseDto hotelResponseDto = new HotelResponseDto();
        BeanUtils.copyProperties(hotel, hotelResponseDto);
        hotelResponseDto.setName(hotel.getHotelName());
        hotelResponseDto.setRating(hotel.getRatings());
        hotelResponseDto.setImages(hotel.getImageUrl());
        return hotelResponseDto;
    }

    public List<HotelResponseDto> toResponseList(List<Hotel> hotels) {
        return hotels.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
