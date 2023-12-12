package org.Checkout;

import org.Customer.Customer;
import org.Customer.CustomerList;
import org.order.Order;
import org.order.OrderList;

import java.util.ArrayList;

public class PickUpPoint implements Runnable{
    private OrderList orderCompleteList;
    private CustomerList customerWaitingList;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public PickUpPoint() {
        orderCompleteList=new OrderList();
        customerWaitingList=new CustomerList();
    }

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
        System.out.println(ANSI_GREEN + "додавання клієнта " +customer.getId()+ ANSI_RESET);

    }
    public void addOrderToCompleteList(Order order){
        synchronized (orderCompleteList)
        {
            orderCompleteList.addToList(order);
            System.out.println(ANSI_GREEN + "додавання замовлення "+order.getId() + ANSI_RESET);
            orderCompleteList.notifyAll();
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
            System.out.println(ANSI_GREEN + "Видаляє замовлення "+order.getId() + ANSI_RESET);
        }

        synchronized (customerWaitingList){
            customerWaitingList.takeOrderAndLeave(order);
        }
        System.out.println(ANSI_GREEN + "Видаляє клієнта " + ANSI_RESET);
        System.out.println("Кількість кастомерів в очікуванні замовлення "+getCustomerWaitingList().getCustomerAmount());


    }
    @Override
    public void run() {
        System.out.println(ANSI_GREEN + "точка видачі запуск потоку" + ANSI_RESET);
        while(true){
            try {

                Thread.sleep(1000); // Зупинка на пів секунди (500 мілісекунд)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            giveOrderToCustomer();
        }
    }
}
