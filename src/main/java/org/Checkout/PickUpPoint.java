package org.Checkout;

import org.Customer.Customer;
import org.Customer.CustomerList;
import org.order.Order;
import org.order.OrderList;

public class PickUpPoint implements Runnable{
    public OrderList orderCompleteList;
    public CustomerList customerWaitingList;

    public OrderList getOrderCompleteList() {
        return orderCompleteList;
    }

    public void setOrderCompleteList(OrderList orderCompleteList) {
        this.orderCompleteList = orderCompleteList;
    }

    public CustomerList getCustomerWaitingList() {
        return customerWaitingList;
    }

    public void setCustomerWaitingList(CustomerList customerWaitingList) {
        this.customerWaitingList = customerWaitingList;
    }

    public void giveOrderToCustomer(Order order)
    {
        if(!orderCompleteList.isEmptyOrderQueue() && orderCompleteList.containsOrder(order)){
            orderCompleteList.removeFromList(order);
        }
        customerWaitingList.takeOrderAndLeave(order);
    }

    public void addCustomerToWaitList(Customer customer){
        synchronized (customerWaitingList)
        {
            customerWaitingList.addToList(customer);
        }
    }
    public void addOrderToCompleteList(Order order){
        synchronized (orderCompleteList)
        {
            orderCompleteList.addToList(order);
        }
    }
    public void giveOrderToCustomer()
    {
        Order order;

        synchronized (orderCompleteList){
            while (orderCompleteList.isEmptyOrderQueue()){
                try {
                    orderCompleteList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            order = orderCompleteList.getOrderQueue().get(0);
            orderCompleteList.removeFromList(order);
        }

        synchronized (customerWaitingList){
            customerWaitingList.takeOrderAndLeave(order);
        }

    }
    @Override
    public void run() {
        while(true){
            giveOrderToCustomer();
        }
    }
}
