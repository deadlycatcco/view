package org.Checkout;

import org.PizzaRestaurant.Kitchen;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CheckoutList {
    List<Checkout> checkouts = new ArrayList<>();
    List<Thread> checkoutsThreads;
    PickUpPoint pickUpPoint;
    Thread pickUpPointThread;

    public CheckoutList(List<Checkout> checkouts, PickUpPoint pickUpPoint) {
        this.checkouts = checkouts;
        checkoutsThreads = new ArrayList<>();
        this.pickUpPoint=pickUpPoint;
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
        checkoutsThreads = checkouts.stream()
                .map(x -> new Thread(x))
                .collect(Collectors.toList());

        pickUpPointThread=new Thread(pickUpPoint);

    }
    public void runAllCheckoutThread(){
        createAllCheckoutThread();
        if(!checkoutsThreads.isEmpty()){
            checkoutsThreads.stream().forEach(x->x.start());
        }
        pickUpPointThread.start();
    }

    public List<Thread> getCheckoutsThreads() {
        return checkoutsThreads;
    }
    private List<Checkout> createCheckouts(int numberCheckouts, Kitchen kitchen){
        List<Checkout> checkouts1=new ArrayList<>();
        for(int i=0;i<numberCheckouts;i++){
            checkouts1.add(new Checkout(kitchen,pickUpPoint));
        }
        return checkouts1;
    }

    public PickUpPoint getPickUpPoint() {
        return pickUpPoint;
    }
}
