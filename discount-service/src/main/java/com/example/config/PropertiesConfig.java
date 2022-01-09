package com.example.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("discount-service")
@Getter
@Setter
public class PropertiesConfig {
    private int kids;
    private int student;
}
