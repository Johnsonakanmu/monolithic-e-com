package com.johnsoncodelove.ecomapp.controller;

import com.johnsoncodelove.ecomapp.dto.CartItemRequest;
import com.johnsoncodelove.ecomapp.model.CartItem;
import com.johnsoncodelove.ecomapp.record.ApiResponse;
import com.johnsoncodelove.ecomapp.record.CartItemResponse;
import com.johnsoncodelove.ecomapp.services.cartItem.CartItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@AllArgsConstructor
public class CartItemController {

    private CartItemService cartItemService;

    @PostMapping
    public ResponseEntity<ApiResponse<CartItemResponse>> addToCart(@RequestHeader("X-USER-ID") String userId,
                                                                   @RequestBody CartItemRequest request) {

        CartItemResponse cartItem = cartItemService.addToCart(userId, request);

        ApiResponse<CartItemResponse> response =
                new ApiResponse<>(
                        true,
                        "Product added to cart successfully",
                        cartItem
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @DeleteMapping("/item/{productId}")
    public ResponseEntity<Void> removeCartItem(@RequestHeader("X-USER-ID") String userId,
                                               @PathVariable("productId") Long productId) {

        boolean deleted = cartItemService.deleteItemFromCart(userId, productId);
        return deleted ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping()
    public ResponseEntity<List<CartItem>> getCart(@RequestHeader("X-USER-ID") String userId) {
        return ResponseEntity.ok(cartItemService.getCart(userId));

    }
}
