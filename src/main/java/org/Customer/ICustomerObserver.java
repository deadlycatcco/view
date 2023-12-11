package org.Customer;

import org.order.Order;

public interface ICustomerObserver {

    public void update();
    public int getId();
    public void setId(int id);
   // public Order generatePizzaOrder();
    public Order getOrder();

}
