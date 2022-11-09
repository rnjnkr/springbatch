package com.javadev.test.csv.parser.config.itemprocessor;

import com.javadev.test.csv.parser.model.Order;
import org.springframework.batch.item.ItemProcessor;

public class OrdersProcessor implements ItemProcessor<Order, Order> {
    @Override
    public Order process(Order order) throws Exception {
        return order;

//        if (order.getAmount() > 500) { //This filters records where the amount is greater than 500
//            return order;
//        } else return null;
    }
}
