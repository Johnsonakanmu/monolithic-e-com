package com.johnsoncodelove.ecomapp.record;


import java.math.BigDecimal;

public record CartItemResponse(
        Long id,
        Integer quantity,
        BigDecimal price
) {
}
