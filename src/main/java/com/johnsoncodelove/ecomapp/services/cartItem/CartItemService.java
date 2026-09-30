package com.johnsoncodelove.ecomapp.services.cartItem;

import com.johnsoncodelove.ecomapp.dto.CartItemRequest;
import com.johnsoncodelove.ecomapp.model.CartItem;
import com.johnsoncodelove.ecomapp.record.CartItemResponse;

import java.util.List;

public interface CartItemService {

    public CartItemResponse addToCart(String userId, CartItemRequest request);

    boolean deleteItemFromCart(String userId, Long productId);

    public List<CartItem> getCart(String UserId);

    void clearCart(String userId);
}
