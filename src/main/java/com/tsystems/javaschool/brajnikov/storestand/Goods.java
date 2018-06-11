package com.tsystems.javaschool.brajnikov.storestand;

import lombok.Data;

@Data
public class Goods {
    private int id;
    private String name;
    private Integer price;
    private String description;
    private int leftCount;
}
