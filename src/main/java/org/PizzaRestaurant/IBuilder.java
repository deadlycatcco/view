package org.PizzaRestaurant;

public interface IBuilder {
    void setRoomSize(int n, int m);
    void setCheckoutsAmount(int n);
    PizzaRestaurant getResult();
}
