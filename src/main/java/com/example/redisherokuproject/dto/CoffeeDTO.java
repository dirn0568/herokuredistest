package com.example.redisherokuproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class CoffeeDTO {
    private String name;
    private int price;
    private String title;
}
