package org.Cooks.CookingHandler;

import org.Cooks.Cook;
import org.menu_and_pizza.AbstractProduct;
import org.menu_and_pizza.Pizza;

import java.util.ArrayList;
import java.util.List;

public class MakeSauceHandler extends BaseHandler{
    public void prepare() {
        List<AbstractProduct> products = currentCook.getOrder().getProducts();
        List<Pizza> pizzas = new ArrayList<>();
        for (var pizza : products) {
            if(pizza instanceof Pizza)
                pizzas.add((Pizza) pizza);
        }
        for(var pizza : pizzas) {
            try {
                Thread.sleep(currentCook.getCookingTime()/5);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println("Making sauce by "+ currentCook.getId()+"("+currentCook.getOrder().getId()+")");
            pizza.getNextStage();
        }
        Cook cook = null;
        while(cook == null) {
            cook = (Cook)currentCook.nextCook();
        }
        currentCook.reGiveOrder(cook);
    }
}
