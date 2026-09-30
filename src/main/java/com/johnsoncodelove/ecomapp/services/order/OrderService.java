package com.johnsoncodelove.ecomapp.services.order;

import com.johnsoncodelove.ecomapp.dto.OrderResponse;

import java.util.Optional;

public interface OrderService {

    public OrderResponse createOrder(String userId);
}
