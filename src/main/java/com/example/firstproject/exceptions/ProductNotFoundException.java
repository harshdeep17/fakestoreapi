package com.example.firstproject.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFoundException extends Exception {
    private Long id;
    private String message;

    public ProductNotFoundException(Long id, String message) {
        super(message);
        this.id = id;
    }
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
