package com.tsystems.javaschool.brajnikov.storestand.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsDto {
    private int id;
    private String name;
    private Integer price;
    private String description;
    private int leftCount;
    private int salesCount;
}
