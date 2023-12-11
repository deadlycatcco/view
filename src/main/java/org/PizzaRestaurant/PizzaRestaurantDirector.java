package org.PizzaRestaurant;

public class PizzaRestaurantDirector {
    private PizzaRestaurantBuilder builder;

    public PizzaRestaurantDirector(PizzaRestaurantBuilder builder) {
        this.builder = builder;
    }

    public PizzaRestaurant construct() {
        builder.setRoomSize(50, 50);
        builder.setCheckoutsAmount(5);

        return builder.getResult();
    }
}
