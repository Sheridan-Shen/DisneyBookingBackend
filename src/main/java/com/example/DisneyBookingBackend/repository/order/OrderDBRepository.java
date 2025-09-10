package com.example.DisneyBookingBackend.repository.order;

import com.example.DisneyBookingBackend.models.Comment;
import com.example.DisneyBookingBackend.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Repository
public class OrderDBRepository {
    @Autowired
    private JpaOrderRepository jpaOrderRepository;

    public List<Comment> getCommentsByHotelIdAndThemeName(Integer hotelId, String themeName) {
        return jpaOrderRepository.getCommentsByHotelIdAndThemeName(hotelId, themeName);
    }

    public void createOrder(Order order) {
        jpaOrderRepository.save(order);
    }

    public HashSet<Integer> selectRoomIdsBetweenCheckInAndCheckOut(Integer hotelId, LocalDate checkIn, LocalDate checkOut) {
        List<String> roomIdsStringList = jpaOrderRepository.selectRoomIdsBetweenCheckInAndCheckOut(hotelId, checkIn, checkOut);
        HashSet<Integer> bookedRoomIds = new HashSet<>();
        for (String roomIdsString : roomIdsStringList) {
            String[] roomIdsArray = roomIdsString.split(",");
            for (String roomIdStr : roomIdsArray) {
                try {
                    bookedRoomIds.add(Integer.parseInt(roomIdStr.trim()));
                } catch (NumberFormatException e) {
                    // Log the error and continue processing other IDs
                    System.err.println("Invalid room ID: " + roomIdStr);
                }
            }
        }
        return bookedRoomIds;
    }
}
