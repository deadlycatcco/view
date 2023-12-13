package org.Cooks;
import org.Checkout.PickUpPoint;
import org.Cooks.CookingStrategy.ICookingStrategy;
import org.menu_and_pizza.AbstractProduct;
import org.menu_and_pizza.Pizza;
import org.menu_and_pizza.PizzaCookingStage;
import org.order.Order;
import org.order.OrderStatus;

import java.util.List;

public class Cook implements ICook{
   private int id;
    private Boolean isFree;
    private Order order;
    private ICookingStrategy strategy;
    private PickUpPoint pickUpPoint;
    private  int cookingTime;

    public Cook(){
        this.isFree = true;
        this.order = null;
        this.strategy = null;
        this.pickUpPoint=null;
    }
    public Cook(Boolean isFree, Order order, ICookingStrategy cookingStrategy,PickUpPoint pickUpPoint){
        this.isFree = isFree;
        this.order = order;
        this.strategy = cookingStrategy;
        this.pickUpPoint=pickUpPoint;
    }
    public void setCookingTime(int time) {
        cookingTime = time;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void addOrder(Order order){
        this.order = order;
        this.setBusy();
    }

    @Override
    public Order getOrder() {
        return order;
    }

    public ICook nextCook() {
        return CookList.findFirstFree();
    }

    public void setStrategy(ICookingStrategy str) {
        this.strategy = str;
    }

    public void cookWithStrategy() {
        this.strategy.cook(this);
    }
    public void reGiveOrder(Cook cook) {
        setFree();
        cook.addOrder(getOrder());

    }
    public void sendCompleteOrder() {
        order.changeStatus(OrderStatus.COMPLETED);
        this.setFree();
        pickUpPoint.addOrderToCompleteList(order);
    }

    public Boolean isFree(){
        return isFree;
    }

    public void setFree() {
        this.isFree = true;
    }
    public void setBusy() {
        this.isFree = false;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public String getAllInfo() {
        String info = String.format("Cook %d is currently ",id);
        if(isFree) {
            info += "free.\n";
            return info;
        }
        else info+="busy.\n";
        List<AbstractProduct> aplist = order.getProducts();
        Pizza pizza = null;
        for(var ap: aplist)
            if(ap instanceof Pizza)
                pizza = (Pizza)ap;
        PizzaCookingStage cookingStage = null;
        if(pizza!=null) {
            cookingStage = pizza.whichStepToDo();
        }
        info+= String.format("Currently working at Order %d \n,making pizza on stage %s",order.getId(),cookingStage);
        return info;
    }
    public void setPickUpPoint(PickUpPoint pickUpPoint) {
        this.pickUpPoint = pickUpPoint;
    }
}
