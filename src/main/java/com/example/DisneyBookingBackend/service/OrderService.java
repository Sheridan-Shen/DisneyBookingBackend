package com.example.DisneyBookingBackend.service;

import com.example.DisneyBookingBackend.models.Order;
import com.example.DisneyBookingBackend.models.Status;
import com.example.DisneyBookingBackend.repository.order.OrderDBRepository;
import com.example.DisneyBookingBackend.repository.room.RoomDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
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

    @Autowired
    private UserService userService;

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

        //  去数据库请求UserID为1的信息，再设置UserName和联系人
        String UserName = userService.getUserNameById(order.getUserId());
        String phone = userService.getPhoneById(order.getUserId());
        order.setUserName(UserName);
        order.setPhone(phone);

        order.setStatus(Status.CONFIRMED);

        orderDBRepository.createOrder(order);
    }

    public void addComment(Integer orderId, Float rating, String comment) {
        Order order = orderDBRepository.getOrderById(orderId);
        order.setRating(rating);
        order.setComment(comment);
        Instant now = Instant.now();
        order.setRatingDate(now);
        orderDBRepository.createOrder(order);
    }

    public void cancelOrder(Integer orderId) {
        Order order = orderDBRepository.getOrderById(orderId);
        order.setStatus(Status.CANCELLED);
        orderDBRepository.createOrder(order);
    }
}

