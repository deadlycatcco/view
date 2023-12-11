package org.order.generation;

import org.menu_and_pizza.AbstractProduct;
import org.menu_and_pizza.Menu;
import org.menu_and_pizza.Pizza;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class OnePizzaStrategy implements IOrderStrategy{

    public List<AbstractProduct> generate() {

        List<AbstractProduct> orderProducts = new ArrayList<>();
        List<Pizza> pizzas = Menu.getPizzas();

        int pizzaNumber = ThreadLocalRandom.current().nextInt(0, pizzas.size());
        orderProducts.add(pizzas.get(pizzaNumber));

        return orderProducts;
    }
}
