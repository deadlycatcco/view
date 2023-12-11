package org.Customer;

import org.order.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerList {
    List<Customer> customerArrayList;

    public CustomerList(ArrayList<Customer> customerArrayList) {
        this.customerArrayList = customerArrayList;
    }

    public void takeOrderAndLeave(Order order){
        Optional<Customer> customer  =customerArrayList.stream().filter(x->x.getOrder().equals(order)).findFirst();
        if(customer.isPresent()){
            deleteFromList(customer.get());
        }
    }
    public void addToList(Customer customer){
        customerArrayList.add(customer);
    }
    public void deleteFromList(Customer customer){
        customerArrayList.remove(customer);
    }
}
