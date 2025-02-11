package com.example.microservices.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("limits-service")
public class Configuration {
    private Integer minimum;
    private Integer maximum;

}
