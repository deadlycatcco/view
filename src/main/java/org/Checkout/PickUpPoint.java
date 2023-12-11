package org.Checkout;

import org.Customer.Customer;
import org.Customer.CustomerList;
import org.order.Order;
import org.order.OrderList;

import java.util.ArrayList;

public class PickUpPoint {
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
        customerWaitingList.addToList(customer);
    }



}
