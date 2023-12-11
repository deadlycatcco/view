package org.order.generation;

import org.menu_and_pizza.AbstractProduct;
import org.menu_and_pizza.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MultiplePizzaAndBeverageStrategy implements IOrderStrategy{

    public List<AbstractProduct> generate() {
        int pizzaCapacity = ThreadLocalRandom.current().nextInt(1, Menu.getPizzas().size() + 1);
        int beveragesCapacity = ThreadLocalRandom.current().nextInt(1, Menu.getBeverages().size() + 1);

        List<AbstractProduct> orderProducts = new ArrayList<>();

        OnePizzaStrategy pizzaStrategy = new OnePizzaStrategy();
        for(int i = 0; i< pizzaCapacity; i++) {
            orderProducts.addAll(pizzaStrategy.generate());
        }

        OneBeverageStrategy beverageStrategy = new OneBeverageStrategy();
        for(int i = 0; i < beveragesCapacity; i++) {
            orderProducts.addAll(beverageStrategy.generate());
        }

        return orderProducts;
    }
}
