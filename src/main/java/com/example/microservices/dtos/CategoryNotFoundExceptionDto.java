package com.example.microservices.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CategoryNotFoundExceptionDto {
    private LocalDateTime timestamp;
    private String error;
    private String message;
}
