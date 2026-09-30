package com.johnsoncodelove.ecomapp.controller;

import com.johnsoncodelove.ecomapp.dto.OrderResponse;
import com.johnsoncodelove.ecomapp.record.ApiResponse;
import com.johnsoncodelove.ecomapp.services.order.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;


    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> createOrder(
            @RequestHeader("X-USER-ID") String userId
    ) {

        OrderResponse orderResponse = orderService.createOrder(userId);

        ApiResponse<OrderResponse> response =
                new ApiResponse<>(
                        true,
                        "Order created successfully",
                        orderResponse
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

}
