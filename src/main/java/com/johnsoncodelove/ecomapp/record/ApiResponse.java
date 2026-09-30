package com.johnsoncodelove.ecomapp.record;

public record ApiResponse<T>(
        boolean success,
        String message,
        T data
) {
}