package com.example.DisneyBookingBackend.models.mapper;

import com.example.DisneyBookingBackend.models.Room;
import com.example.DisneyBookingBackend.models.dto.SampleRoomDto;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {
    public SampleRoomDto toSampleRoomDto(Room room) {
        SampleRoomDto dto = new SampleRoomDto();
        dto.setRoomId(room.getHotelId());
        dto.setRoomName(room.getRoomName());
        dto.setPrice(room.getPrice());
        dto.setImageUrls(room.getImageUrls());
        dto.setRating(room.getRating());
        dto.setDescription(room.getDescription());
        return dto;
    }
}
