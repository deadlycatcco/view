package org.Cooks.CookingStrategy;

import org.Cooks.Cook;
import org.Cooks.CookingHandler.*;
import org.menu_and_pizza.Pizza;
import org.menu_and_pizza.PizzaCookingStage;
import org.order.Order;
import org.order.OrderStatus;

public class MultipleCookingStrategy implements ICookingStrategy {
    public void cook(Cook cook) {
        Order order = cook.getOrder();
        if(order.getStatus()!=OrderStatus.PREPARING) {
            order.changeStatus(OrderStatus.PREPARING);
        }
        Pizza pizza = new Pizza();
        for(var ab : order.getProducts()) {
            if(ab instanceof Pizza) {
                pizza = (Pizza) ab;
                BaseHandler nextHandler = null;
                if(pizza.whichStepToDo() == PizzaCookingStage.START) {
                    nextHandler = new MakeDoughHandler();
                }else if(pizza.whichStepToDo() == PizzaCookingStage.DOUGH) {
                    nextHandler = new MakeSauceHandler();
                }
                else if(pizza.whichStepToDo() == PizzaCookingStage.SAUCE) {
                    nextHandler = new PutToppingsHandler();
                }
                else if(pizza.whichStepToDo() == PizzaCookingStage.TOPPINGS) {
                    nextHandler = new BakeHandler();
                }else if(pizza.whichStepToDo() == PizzaCookingStage.BAKED) {
                    nextHandler = new PackagingHandler();
                }
                else return;
                nextHandler.setCook(cook);
                nextHandler.prepare();
                break;
            }
            else  {
                cook.sendCompleteOrder();
                cook.setFree();
                return;
            }
        }


    }
}
