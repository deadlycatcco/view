package org.Customer;

import org.Checkout.Checkout;
import org.Checkout.CheckoutList;

import java.util.Comparator;

public class CustomerManager {
    private CheckoutList checkoutList;

    public CustomerManager(CheckoutList checkoutList){
        this.checkoutList=checkoutList;
    }
    public Checkout findBestCheckoutQueue(){
        return checkoutList.getCheckouts().stream()
                .min(Comparator.comparingInt(Checkout::getCustomersCount))
                .orElse(null);
    }

    public int sendCustomerToCheckout(Customer customer){

        Checkout ch=findBestCheckoutQueue();

        if(ch!=null){
            synchronized (ch.getCustomers()) {
                ch.getCustomers().add(customer);
                ch.getCustomers().notifyAll();
            }
        }
        System.out.println("кастомер пішов до каси "+ ch.getId()+", к-сть кастомерів: "+ch.getCustomersCount());
        return ch.getId();
    }
}
