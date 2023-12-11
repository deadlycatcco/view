package org.Cooks;
import org.Cooks.CookingStrategy.ICookingStrategy;
import org.order.Order;

public class Cook implements ICook{
    private Boolean isFree;
    private Order order;
    private ICookingStrategy strategy;

    public Cook(){
        this.isFree = true;
        this.order = null;
        this.strategy = null;
    }
    public Cook(Boolean isFree, Order order, ICookingStrategy cookingStrategy){
        this.isFree = isFree;
        this.order = order;
        this.strategy = cookingStrategy;
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

    public void sendCompleteOrder() {
        order = null;
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


}
