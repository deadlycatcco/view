package org.order.generation;

import org.order.Order;

public final class OrderGenerator {

    private static OrderGenerator instance;
    private int id = 1;
    private StrategyList strategies;

    public OrderGenerator() {
        strategies = new StrategyList();
    }

    public Order generateOrder() {
        Order order = new Order(this.id, strategies.getRandomStrategy().generate());
        this.id++;
        return order;
    }

    public static OrderGenerator getInstance() {
        if(instance == null) {
            instance = new OrderGenerator();
        }
        return instance;
    }

}
