package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Discount {

    private Integer instanceId;
    private int kids;
    private int student;

    public Discount() {

    }
}
