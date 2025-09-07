package com.example.DisneyBookingBackend.repository.room;

import com.example.DisneyBookingBackend.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRoomRepository extends JpaRepository<Room, Integer> {
}

