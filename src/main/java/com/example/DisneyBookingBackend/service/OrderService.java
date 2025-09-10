package com.example.DisneyBookingBackend.service;

import com.example.DisneyBookingBackend.models.Order;
import com.example.DisneyBookingBackend.models.Status;
import com.example.DisneyBookingBackend.repository.order.OrderDBRepository;
import com.example.DisneyBookingBackend.repository.room.RoomDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderDBRepository orderDBRepository;

    @Autowired
    private RoomDBRepository roomDBRepository;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private ThemeService themeService;

    public HashSet<Integer> selectRoomIdsBetweenCheckInAndCheckOut(Integer hotelId, LocalDate checkIn, LocalDate checkOut) {
        return orderDBRepository.selectRoomIdsBetweenCheckInAndCheckOut(hotelId, checkIn, checkOut);
    }

    public void createOrder(Order order) {
        order.setHotelName(hotelService.getHotelNameById(order.getHotelId()));
        order.setThemeId(themeService.getThemeIdByName(order.getThemeName()));

        HashSet<Integer> occupiedRoomIds = selectRoomIdsBetweenCheckInAndCheckOut(order.getHotelId(), order.getCheckIn(), order.getCheckOut());
        List<Integer> allRoomIds = roomDBRepository.getAllRoomIdsByHotelIdAndThemeId(order.getHotelId(), order.getThemeId());

        List<Integer> availableRoomIds = allRoomIds.stream()
                .filter(roomId -> !occupiedRoomIds.contains(roomId))
                .toList();

        if (availableRoomIds.size() < order.getRoomCount()) {
            throw new IllegalArgumentException("Not enough available rooms for the selected theme and dates.");
        }

        List<Integer> selectedRoomIds = availableRoomIds.subList(0, order.getRoomCount());
        order.setRoomIds(selectedRoomIds.stream()
                .map(String::valueOf)
                .reduce((a, b) -> a + "," + b)
                .orElse(""));

        List<Integer> selectedRoomNumbers = roomDBRepository.getRoomNumbersByRoomIds(selectedRoomIds);
        order.setRoomNumbers(selectedRoomNumbers.stream()
                .map(String::valueOf)
                .reduce((a, b) -> a + "," + b)
                .orElse(""));

        order.setRoomName(roomDBRepository.selectRoomNameByRoomId(selectedRoomIds.get(0)));

        // Todo 去数据库请求User信息
        order.setUserName("Li Hua");
        order.setPhone("13800138000");

        order.setStatus(Status.CONFIRMED);

        orderDBRepository.createOrder(order);
    }
}

