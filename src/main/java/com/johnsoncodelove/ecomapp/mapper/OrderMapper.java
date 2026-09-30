package com.johnsoncodelove.ecomapp.mapper;

import com.johnsoncodelove.ecomapp.dto.OrderItemDto;
import com.johnsoncodelove.ecomapp.dto.OrderResponse;
import com.johnsoncodelove.ecomapp.model.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderMapper {

    public OrderResponse mapToOrderResponse(Order order) {

        return new OrderResponse(
                order.getId(),
                order.getTotalAmount(),
                order.getStatus(),
                order.getItems()
                        .stream()
                        .map(orderItem -> new OrderItemDto(
                                orderItem.getId(),
                                orderItem.getProduct().getId(),
                                orderItem.getQuantity(),
                                orderItem.getPrice(),
                                orderItem.getPrice().multiply(new BigDecimal(orderItem.getId()))
                        ))
                        .toList(),
                order.getCreatedAt()

        );

    }

    }
