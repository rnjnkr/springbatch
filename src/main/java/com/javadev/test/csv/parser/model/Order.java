package com.javadev.test.csv.parser.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Order {
    private int id;
    private int amount;
    private String currency;
    private String comment;
}
