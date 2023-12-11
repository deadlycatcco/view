package org.Customer;

import org.order.Order;

import java.util.ArrayList;
import java.util.List;

public class CustomerList {
    List<Customer> customerArrayList;

    public CustomerList(ArrayList<Customer> customerArrayList) {
        this.customerArrayList = customerArrayList;
    }

    public void takeOrderAndLeave(Order order){

    }
    public void addToList(Customer customer){
        customerArrayList.add(customer);
    }
    public void deleteFromList(Customer customer){
        customerArrayList.remove(customer);
    }
}
