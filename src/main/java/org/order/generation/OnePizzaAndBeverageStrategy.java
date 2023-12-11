package org.order.generation;

import org.menu_and_pizza.AbstractProduct;

import java.util.ArrayList;
import java.util.List;

public class OnePizzaAndBeverageStrategy implements IOrderStrategy {

    public List<AbstractProduct> generate() {

        List<AbstractProduct> orderProducts = new ArrayList<>();

        OnePizzaStrategy pizzaStrategy = new OnePizzaStrategy();
        orderProducts.addAll(pizzaStrategy.generate());

        OneBeverageStrategy beverageStrategy = new OneBeverageStrategy();
        orderProducts.addAll(beverageStrategy.generate());

        return orderProducts;
    }
}
