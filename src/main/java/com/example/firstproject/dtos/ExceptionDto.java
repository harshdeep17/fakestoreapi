package com.example.firstproject.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExceptionDto {
    private LocalDateTime timestamp;
    private String error;
    private String message;
}
