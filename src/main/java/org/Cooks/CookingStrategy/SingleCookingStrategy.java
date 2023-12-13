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
            if(ap == null) {
                System.out.println("Замовлення пусте");
                order.changeStatus(OrderStatus.COMPLETED);
                cook.sendCompleteOrder();
                cook.setFree();
                return;
            }
            List<Pizza> pizzas = new ArrayList<>();
            for (var pizza : ap) {
                if(pizza instanceof Pizza)
                    pizzas.add((Pizza) pizza);
            }
            for (var pizza : pizzas) {
                PizzaCookingStage currentStage = pizza.whichStepToDo();
                while (currentStage != null) {
                    System.out.println(pizza.getName()+" is at " + currentStage+" by "+cook.getId());
                    try {
                        Thread.sleep(cook.getCookingTime()/5);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    currentStage = pizza.getNextStage();
                    //щось на юайці
                }
            }
            cook.sendCompleteOrder();
            cook.setFree();
        }
    }
}
