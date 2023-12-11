package org.Cooks;
import org.Cooks.CookingStrategy.ICookingStrategy;
import org.order.*;
public interface ICook {

    public void addOrder(Order order);
    public Order getOrder();
    public ICook nextCook();
    public void setStrategy(ICookingStrategy str);
    public void cookWithStrategy();
    public void sendCompleteOrder();
    public Boolean isFree();
    public void setFree();
    public void setBusy();

}