package com.johnsoncodelove.ecomapp.respository;

import com.johnsoncodelove.ecomapp.model.CartItem;
import com.johnsoncodelove.ecomapp.model.Product;
import com.johnsoncodelove.ecomapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByUserAndProduct(User user, Product product);

    void deleteByUserAndProduct(Object user, Object product);

    boolean existsByUserAndProduct(User user, Product product);

    List<CartItem> findByUser(User cartItem);

    void deleteByUser(User user);
}
