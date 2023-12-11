package org.Customer;

import org.Checkout.Checkout;
import org.Checkout.CheckoutList;

import java.util.Comparator;

public class CustomerManager {
    private final CustomerGenerator customerGenerator;
    private CheckoutList checkoutList;

    public CustomerManager(CheckoutList checkoutList){
        this.checkoutList=checkoutList;
        customerGenerator=new CustomerGenerator();
    }
    public Checkout findBestCheckoutQueue(){
        return checkoutList.getCheckouts().stream()
                .min(Comparator.comparingInt(Checkout::getCustomersCount))
                .orElse(null);
    }

    public void sendCustomerToCheckout(){
        Customer customer =customerGenerator.generateCustomer();
        Checkout ch=findBestCheckoutQueue();

        if(ch!=null){
            synchronized (ch.getCustomers()) {
                ch.getCustomers().add(customer);
                ch.getCustomers().notifyAll();
            }
        }
        System.out.println("кастомер пішов до каси "+ ch.getId()+", к-сть кастомерів: "+ch.getCustomersCount());
    }
}
