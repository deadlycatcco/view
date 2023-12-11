package org.PizzaRestaurant;

import org.Checkout.CheckoutList;
import org.order.OrderBoard;
public class PizzaRestaurant {
    private CheckoutList checkoutList;
    private Kitchen kitchen;
    private int minPizzaTime;
    private OrderBoard orderBoard;
    private RestaurantSize restaurantSize;
    public PizzaRestaurant(PizzaRestaurant pizzaRestaurant) {
        this.checkoutList = pizzaRestaurant.getCheckoutList();
        this.kitchen = pizzaRestaurant.getKitchen();
        this.minPizzaTime = pizzaRestaurant.getMinPizzaTime();
        this.orderBoard = pizzaRestaurant.getOrderBoard();
        this.restaurantSize = pizzaRestaurant.getRestaurantSize();
    }
    public PizzaRestaurant(CheckoutList checkoutList, Kitchen kitchen, int minPizzaTime, OrderBoard orderBoard, RestaurantSize restaurantSize) {
        this.checkoutList = checkoutList;
        this.kitchen = kitchen;
        this.minPizzaTime = minPizzaTime;
        this.orderBoard = orderBoard;
        this.restaurantSize = restaurantSize;
    }

    public CheckoutList getCheckoutList() {
        return checkoutList;
    }

    public void setCheckoutList(CheckoutList checkoutList) {
        this.checkoutList = checkoutList;
    }

    public Kitchen getKitchen() {
        return kitchen;
    }

    public void setKitchen(Kitchen kitchen) {
        this.kitchen = kitchen;
    }

    public int getMinPizzaTime() {
        return minPizzaTime;
    }

    public void setMinPizzaTime(int minPizzaTime) {
        this.minPizzaTime = minPizzaTime;
    }

    public OrderBoard getOrderBoard() {
        return orderBoard;
    }

    public void setOrderBoard(OrderBoard orderBoard) {
        this.orderBoard = orderBoard;
    }

    public RestaurantSize getRestaurantSize() {
        return restaurantSize;
    }

    public void setRestaurantSize(RestaurantSize restaurantSize) {
        this.restaurantSize = restaurantSize;
    }
}
