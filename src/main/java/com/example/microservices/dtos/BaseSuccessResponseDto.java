package com.example.microservices.dtos;

import lombok.Data;

@Data
public class BaseSuccessResponseDto<T> {
    private String status;
    private String message;
    private String timestamp;
    private String requestId;
    private T data;
}
