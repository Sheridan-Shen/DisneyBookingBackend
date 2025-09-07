package com.example.DisneyBookingBackend.repository.order;

import com.example.DisneyBookingBackend.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends JpaRepository<Order, Integer> {
}
