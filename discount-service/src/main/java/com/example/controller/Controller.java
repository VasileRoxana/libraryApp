package com.example.controller;

import com.example.config.PropertiesConfig;
import com.example.model.Discount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.env.Environment;

@RestController
public class Controller {
    @Autowired
    private PropertiesConfig configuration;
    @Autowired
    Environment environment;

    @GetMapping("/discount")
    public Discount getDiscount(){
        Discount discount = new Discount();
        discount.setKids(configuration.getKids());
        discount.setStudent(configuration.getStudent());
        discount.setInstanceId(Integer.parseInt(environment.getProperty("server.port")));
        return discount;
    }
}
