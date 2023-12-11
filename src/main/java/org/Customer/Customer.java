package org.Customer;

import org.order.Order;

public class Customer implements ICustomerObserver{
    private Order order;
    private int id;
    public Customer(Order order, int id){
        this.order = order;
        this.id=id;
    }

    @Override
    public void update() {

        System.out.println("Customer "+id+", йду по піцу");
        //напевно треба метод move to
    }
   public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "order=" + order +
                ", id=" + id +
                '}';

    }

    public Order getOrder() {
        return order;
    }
}


