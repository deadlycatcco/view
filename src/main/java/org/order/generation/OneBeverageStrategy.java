package org.order.generation;

import org.menu_and_pizza.AbstractProduct;
import org.menu_and_pizza.Beverage;
import org.menu_and_pizza.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class OneBeverageStrategy implements IOrderStrategy{
    public List<AbstractProduct> generate() {

        List<AbstractProduct> orderProducts = new ArrayList<>();
        List<Beverage> beverages = Menu.getBeverages();

        int beverageNumber = ThreadLocalRandom.current().nextInt(0, beverages.size());
        orderProducts.add(beverages.get(beverageNumber));

        return orderProducts;
    }
}
