package com.johnsoncodelove.ecomapp.services.order;

import com.johnsoncodelove.ecomapp.dto.OrderResponse;
import com.johnsoncodelove.ecomapp.mapper.OrderMapper;
import com.johnsoncodelove.ecomapp.model.*;
import com.johnsoncodelove.ecomapp.respository.OrderRepository;
import com.johnsoncodelove.ecomapp.respository.UserRepository;
import com.johnsoncodelove.ecomapp.services.cartItem.CartItemService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{
    private OrderRepository orderRepository;
    private final CartItemService cartItemService;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public OrderResponse createOrder(String userId) {

        // Validate cart
        List<CartItem> cartItems = cartItemService.getCart(userId);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        // Validate user
        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Calculate total price
        BigDecimal totalPrice = cartItems.stream()
                .map(item ->
                        item.getPrice()
                                .multiply(
                                        BigDecimal.valueOf(item.getQuantity())
                                )
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Create Order
        Order order = new Order();

        order.setUser(user);
        order.setStatus(OrderStatus.CONFORMED);
        order.setTotalAmount(totalPrice);

        // Create Order Items
        List<OrderItem> orderItems = cartItems.stream()
                .map(item -> new OrderItem(
                        null,
                        item.getProduct(),
                        item.getQuantity(),
                        item.getPrice(),
                        order
                ))
                .collect(Collectors.toList());

        order.setItems(orderItems);

        // Save Order
        Order savedOrder = orderRepository.save(order);

        // Clear cart
        cartItemService.clearCart(userId);

        // Return response
        return orderMapper.mapToOrderResponse(savedOrder);
    }
}
