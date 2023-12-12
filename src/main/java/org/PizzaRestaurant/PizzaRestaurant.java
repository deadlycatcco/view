package org.PizzaRestaurant;
import org.Checkout.PickUpPoint;
import org.Cooks.CookList;
import org.menu_and_pizza.Menu;
import org.order.Order;
import org.order.OrderBoard;
import org.Checkout.CheckoutList;
public class PizzaRestaurant {
    private Kitchen kitchen;

    private CookList cookList;
    private RestaurantSize restaurantSize;
    private PickUpPoint pickUpPoint;
    private CheckoutList checkoutList;

    private OrderBoard orderBoard;
    private Menu menu;
    private int minPizzaTime;
  /*  public PizzaRestaurant(PizzaRestaurant pizzaRestaurant) {
        this.checkoutList = pizzaRestaurant.getCheckoutList();
        this.kitchen = pizzaRestaurant.getKitchen();
        this.minPizzaTime = pizzaRestaurant.getMinPizzaTime();
        this.orderBoard = pizzaRestaurant.getOrderBoard();
        this.restaurantSize = pizzaRestaurant.getRestaurantSize();
    }*/
    public PizzaRestaurant( Kitchen kitchen,CookList cookList, RestaurantSize restaurantSize,PickUpPoint pickUpPoint,
                            CheckoutList checkoutList, OrderBoard orderBoard, Menu menu, int minPizzaTime) {
        this.kitchen = kitchen;
        this.cookList=cookList;
        this.restaurantSize = restaurantSize;
        this.pickUpPoint=pickUpPoint;
        this.checkoutList = checkoutList;
        this.orderBoard=orderBoard;
        this.menu=menu;
        this.minPizzaTime = minPizzaTime;
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
