package com.johnsoncodelove.ecomapp.respository;

import com.johnsoncodelove.ecomapp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
