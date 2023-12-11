package org.Cooks.CookingHandler;

import org.menu_and_pizza.AbstractProduct;
import org.menu_and_pizza.Pizza;

import java.util.ArrayList;
import java.util.List;

public class MakeDoughHandler extends BaseHandler{
    public void prepare() {
        List<AbstractProduct> products = currentCook.getOrder().getProducts();
        List<Pizza> pizzas = new ArrayList<>();
        for (var pizza : products)
            pizzas.add((Pizza) pizza);
        for(var pizza : pizzas) {
            synchronized (this) {
                try {
                    wait(1000);
                    System.out.println("Making dough by "+ currentCook.getId());
                    pizza.getNextStage();
                }catch (Exception ex) {
                    System.out.println(ex);
                }
            }

        }
    }
}
