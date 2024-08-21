package com.example.microservices.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Limit {
    private Integer minimum;
    private Integer maximum;
    public Limit(Integer minimum, Integer maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }
}
