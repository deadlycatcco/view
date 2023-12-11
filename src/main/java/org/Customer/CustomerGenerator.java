package org.Customer;

import org.order.Order;
import org.order.generation.OrderGenerator;

public final class CustomerGenerator {

    private OrderGenerator orderGenerator;
    private static CustomerGenerator instance;

    public CustomerGenerator() {
        orderGenerator = OrderGenerator.getInstance();
    }

    public Customer generateCustomer(){
        Order order = orderGenerator.generateOrder();
        return new Customer(order, order.getId());
    }

    public static CustomerGenerator getInstance() {
        if(instance == null) {
            instance = new CustomerGenerator();
        }
        return instance;
    }
}
