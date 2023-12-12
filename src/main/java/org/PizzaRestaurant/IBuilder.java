package org.PizzaRestaurant;

import org.Checkout.CheckoutList;
import org.Checkout.PickUpPoint;
import org.Cooks.CookList;
import org.menu_and_pizza.Menu;
import org.order.OrderBoard;

public interface IBuilder {
    void setKitchen(Kitchen kitchen);
    void setCooks(CookList cookList);
    void setRoomSize(RestaurantSize restaurantSize);
    void setPickUpPoint(PickUpPoint pickUpPoint);
    void setCheckoutList(CheckoutList checkoutList);

    void setOrderBoard(OrderBoard orderBoard);
    void setMenu(Menu menu);
    void setMinPizzaTima(int n);


    PizzaRestaurant getResult();


}
