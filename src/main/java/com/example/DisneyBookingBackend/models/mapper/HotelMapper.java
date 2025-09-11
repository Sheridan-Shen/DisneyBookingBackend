package com.example.DisneyBookingBackend.models.mapper;

import com.example.DisneyBookingBackend.models.Hotel;
import com.example.DisneyBookingBackend.models.dto.HotelResponseDto;
import com.example.DisneyBookingBackend.repository.order.OrderDBRepository;
import com.example.DisneyBookingBackend.repository.room.RoomDBRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class HotelMapper {
    @Autowired
    private RoomDBRepository roomDBRepository;

    @Autowired
    private OrderDBRepository orderDBRepository;

    public HotelResponseDto toResponse(Hotel hotel) {
        HotelResponseDto hotelResponseDto = new HotelResponseDto();
        BeanUtils.copyProperties(hotel, hotelResponseDto);
        hotelResponseDto.setName(hotel.getHotelName());
        hotelResponseDto.setRating(hotel.getRatings());
        hotelResponseDto.setImages(hotel.getImageUrl());

        List<BigDecimal> roomPrices = roomDBRepository.selectRoomPricesByHotelId(hotel.getId());
        BigDecimal minimumPrice = roomPrices.stream()
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.valueOf(1314));
        hotelResponseDto.setMinimumPrice(minimumPrice);

        List<Float> ratings = orderDBRepository.selectRatingsByHotelId(hotel.getId());
        if (!ratings.isEmpty()) {
            float averageRating = (float) ratings.stream()
                    .mapToDouble(Float::doubleValue)
                    .average()
                    .orElse(0.0);
            hotelResponseDto.setRating(averageRating);
        } else {
            hotelResponseDto.setRating(4.0f);
        }

        return hotelResponseDto;
    }

    public List<HotelResponseDto> toResponseList(List<Hotel> hotels) {
        return hotels.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
