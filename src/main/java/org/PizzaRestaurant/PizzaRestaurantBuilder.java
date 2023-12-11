package org.PizzaRestaurant;
import org.Checkout.CheckoutList;
import org.order.OrderBoard;

public class PizzaRestaurantBuilder implements IBuilder{

    private CheckoutList checkoutList;
    private Kitchen kitchen;
    private int minPizzaTime;
    private OrderBoard orderBoard;
    private RestaurantSize restaurantSize;


    public void setRoomSize(int n, int m) {
        restaurantSize.setHeight(n);
        restaurantSize.setWidth(m);
    }

    @Override
    public void setCheckoutsAmount(int n) {
        //checkoutList = new CheckoutList(n);
    }

    @Override
    public PizzaRestaurant getResult() {
        return new PizzaRestaurant(checkoutList, kitchen, minPizzaTime, orderBoard, restaurantSize);
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
