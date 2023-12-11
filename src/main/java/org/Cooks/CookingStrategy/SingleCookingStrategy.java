package org.Cooks.CookingStrategy;

import org.Cooks.Cook;
import org.order.Order;
import org.order.OrderStatus;
import org.menu_and_pizza.AbstractProduct;
import org.menu_and_pizza.Pizza;
import org.menu_and_pizza.PizzaCookingStage;

import java.util.ArrayList;
import java.util.List;

public class SingleCookingStrategy implements ICookingStrategy {
    public void cook(Cook cook) {
        if(!cook.isFree() && cook.getOrder()!=null){
            Order order = cook.getOrder();
            order.changeStatus(OrderStatus.PREPARING);
            List<AbstractProduct> ap = order.getProducts();
            List<Pizza> pizzas = new ArrayList<>();
            for (var pizza : ap)
                pizzas.add((Pizza) pizza);
            for (var pizza : pizzas) {
                PizzaCookingStage currentStage = pizza.whichStepToDo();
                while (currentStage != null) {
                    synchronized (this) {
                        try {
                            System.out.println(pizza.getName()+" is at " + currentStage+" by "+cook.hashCode());
                            wait(1000);
                            currentStage = pizza.getNextStage();
                        } catch (Exception ex) {
                            System.out.println("Error");
                            return;
                        }
                    }
                }
            }
            order.changeStatus(OrderStatus.COMPLETED);
            cook.sendCompleteOrder();
            cook.setFree();
        }
    }
}
