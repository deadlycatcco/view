package org.Checkout;

import org.Customer.Customer;
import org.Customer.ICustomerObserver;
import org.order.Order;
import org.PizzaRestaurant.Kitchen;

import java.util.LinkedList;
import java.util.Queue;

public class Checkout implements Runnable{
    private int id;
    static int countCheckout=0;
    private Kitchen kitchen;
    private PickUpPoint pickUpPoint;
    private Queue <ICustomerObserver> customers=new LinkedList<>();
    public Checkout(Kitchen kitchen, PickUpPoint pickUpPoint) {
        this.kitchen=kitchen;
        this.pickUpPoint=pickUpPoint;
        countCheckout++;
        this.setId(countCheckout);
    }

    public Queue<ICustomerObserver> getCustomers() {
        return customers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addToQueueCustomers(ICustomerObserver customer){
        customers.add(customer);
    }

    public int getCustomersCount(){
        if(customers!=null){
        return customers.size();}
        return 0;
    }


    public void acceptOrder()
    {
        Customer customer;
        synchronized (customers){
            while (customers.isEmpty()){
                try {
                    customers.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
             customer= (Customer) customers.poll();
        }
        Order order=customer.getOrder();

        synchronized (kitchen.getOrderQueue()){
            kitchen.addOrderToQueue(order);
        }

        synchronized (pickUpPoint.getCustomerWaitingList()){
            pickUpPoint.addCustomerToWaitList(customer);
        }
    }

    @Override
    public void run() {
        while (true){
            acceptOrder();
        }
    }
}
