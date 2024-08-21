package com.example.microservices.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProductNotFoundExceptionDto {
    private LocalDateTime timestamp;
    private String error;
    private String message;
}