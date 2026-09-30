package com.johnsoncodelove.ecomapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "orderItems")
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_d", nullable = false)
    private Product product;
    private Integer quantity;
    private BigDecimal price;
    @ManyToOne()
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
