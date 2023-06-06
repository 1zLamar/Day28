package com.example.day28.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OrderDto {

    private Integer productId;
    private Integer quantity;

}