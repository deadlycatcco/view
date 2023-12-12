package org.PizzaRestaurant;
import org.Checkout.CheckoutList;
import org.Checkout.PickUpPoint;
import org.Cooks.CookList;
import org.Customer.Customer;
import org.menu_and_pizza.Menu;
import org.order.OrderBoard;

public class PizzaRestaurantBuilder implements IBuilder{

    private Kitchen kitchen;

    private CookList cookList;
    private RestaurantSize restaurantSize;
    private PickUpPoint pickUpPoint;
    private CheckoutList checkoutList;

    private OrderBoard orderBoard;
    private Menu menu;
    private int minPizzaTime;


    @Override
    public void setKitchen(Kitchen kitchen) {
        this.kitchen=kitchen;
    }

    @Override
    public void setCooks(CookList cookList) {
        this.cookList=cookList;
    }



    public void setRoomSize(RestaurantSize restaurantSize) {
        this.restaurantSize=restaurantSize;
    }

    @Override
    public void setPickUpPoint(PickUpPoint pickUpPoint) {
        this.pickUpPoint=pickUpPoint;

    }
    public void setCheckoutList(CheckoutList checkoutList) {
        this.checkoutList = checkoutList;
    }
    public void setOrderBoard(OrderBoard orderBoard) {
        this.orderBoard = orderBoard;
    }

    @Override
    public void setMenu(Menu menu) {
        this.menu=menu;

    }

    @Override
    public void setMinPizzaTima(int n) {
        minPizzaTime=n;
    }


    @Override
    public PizzaRestaurant getResult() {
        return new PizzaRestaurant (kitchen, cookList,  restaurantSize, pickUpPoint, checkoutList,  orderBoard,  menu,  minPizzaTime);
    }



public CookList getCookList(){
        return cookList;
}

    public CheckoutList getCheckoutList() {
        return checkoutList;
    }


    public Kitchen getKitchen() {
        return kitchen;
    }






    public int getMinPizzaTime() {
        return minPizzaTime;
    }


    public OrderBoard getOrderBoard() {
        return orderBoard;
    }


    public RestaurantSize getRestaurantSize() {
        return restaurantSize;
    }






}
