package org.Checkout;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CheckoutList {
    List<Checkout> checkouts = new ArrayList<>();
    List<Thread> threads;

    public CheckoutList(List<Checkout> checkouts) {
        this.checkouts = checkouts;
        threads = new ArrayList<>();
    }

    public void addToList(Checkout checkout) {
        checkouts.add(checkout);
    }

    public void removeFromList(int checkoutId) {
        checkouts.removeIf(x -> x.getId() == checkoutId);
    }

    public void removeFromList(Checkout checkout) {
        checkouts.remove(checkout);
    }

    public List<Checkout> getCheckouts() {
        return checkouts;
    }

    private void createAllCheckoutThread() {
        threads = checkouts.stream()
                .map(x -> new Thread(x))
                .collect(Collectors.toList());


    }
    public void runAllCheckoutThread(){
        createAllCheckoutThread();
        if(!threads.isEmpty()){
            threads.stream().forEach(x->x.start());
        }
    }

    public List<Thread> getThreads() {
        return threads;
    }
}
