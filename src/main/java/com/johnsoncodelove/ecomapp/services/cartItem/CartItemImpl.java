package com.johnsoncodelove.ecomapp.services.cartItem;

import com.johnsoncodelove.ecomapp.dto.CartItemRequest;
import com.johnsoncodelove.ecomapp.model.CartItem;
import com.johnsoncodelove.ecomapp.model.Product;
import com.johnsoncodelove.ecomapp.model.User;
import com.johnsoncodelove.ecomapp.record.CartItemResponse;
import com.johnsoncodelove.ecomapp.respository.CartItemRepository;
import com.johnsoncodelove.ecomapp.respository.ProductRepository;
import com.johnsoncodelove.ecomapp.respository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class CartItemImpl implements CartItemService{

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public CartItemResponse addToCart(
            String userId,
            CartItemRequest request) {

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        CartItem existingCartItem =
                cartItemRepository.findByUserAndProduct(user, product);

        int newQuantity;

        if (existingCartItem != null) {

            newQuantity =
                    existingCartItem.getQuantity()
                            + request.getQuantity();

        } else {

            newQuantity = request.getQuantity();
        }

        // Check stock against the final quantity
        if (product.getStockQuantity() < newQuantity) {
            throw new RuntimeException("Product out of stock");
        }

        BigDecimal totalPrice = product.getPrice()
                .multiply(BigDecimal.valueOf(newQuantity));

        CartItem cartItem;

        if (existingCartItem != null) {

            existingCartItem.setQuantity(newQuantity);
            existingCartItem.setPrice(totalPrice);

            cartItem = existingCartItem;

        } else {

            cartItem = new CartItem();

            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(newQuantity);
            cartItem.setPrice(totalPrice);
        }

        CartItem savedCartItem =
                cartItemRepository.save(cartItem);

        return new CartItemResponse(
                savedCartItem.getId(),
                savedCartItem.getQuantity(),
                savedCartItem.getPrice()
        );
    }

    @Override
    public boolean deleteItemFromCart(String userId, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!cartItemRepository.existsByUserAndProduct(user, product)) {
            return false;
        }

        cartItemRepository.deleteByUserAndProduct(user, product);
        return true;
    }

    @Override
    public List<CartItem> getCart(String userId) {
       User user = userRepository.findById(Long.valueOf(userId))
               .orElseThrow(() -> new RuntimeException("User not found"));

       return cartItemRepository.findByUser(user);
    }

    @Override
    public void clearCart(String userId) {
        userRepository.findById(Long.valueOf(userId)).ifPresent(
        cartItemRepository::deleteByUser
        );
    }


}
