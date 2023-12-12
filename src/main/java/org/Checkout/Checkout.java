package org.Checkout;

import org.Customer.Customer;
import org.Customer.ICustomerObserver;

import org.PizzaRestaurant.Kitchen;
import org.order.Order;

import java.util.LinkedList;
import java.util.Queue;

public class Checkout implements Runnable{
    private int id;
    static int countCheckout=0;
    private Kitchen kitchen;
    private PickUpPoint pickUpPoint;
    private Queue <ICustomerObserver> customers=new LinkedList<>();

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
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
        System.out.println(ANSI_GREEN + "прийняття замовлення "+order.getId() + ANSI_RESET);
        synchronized (pickUpPoint.getCustomerWaitingList()){
            pickUpPoint.addCustomerToWaitList(customer);

            System.out.println("-----------------------"+kitchen.getOrderCount());
        }
    }

    @Override
    public void run() {
        while (true){

                try {

                    Thread.sleep(1000); // Зупинка на пів секунди (500 мілісекунд)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            if(kitchen.getOrderCount()<10) {
                System.out.println("-----------------------"+kitchen.getOrderCount());
                acceptOrder();
                try {
                    Thread.sleep(5000);
                } catch (Exception ex) {
                }
            }
        }
    }
}
