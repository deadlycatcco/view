package org.PizzaRestaurant;

import org.Checkout.Checkout;
import org.Checkout.CheckoutList;
import org.Checkout.PickUpPoint;
import org.Cooks.Cook;
import org.Cooks.CookList;
import org.Cooks.CookingStrategy.ICookingStrategy;
import org.Cooks.CookingStrategy.SingleCookingStrategy;
import org.Cooks.ICook;
import org.menu_and_pizza.Menu;
import org.order.OrderBoard;

import java.util.ArrayList;
import java.util.List;

public class PizzaRestaurantDirector {
  /*  private PizzaRestaurantBuilder builder;

    public PizzaRestaurantDirector(PizzaRestaurantBuilder builder) {
        this.builder = builder;
    }
*/
    public void construct(PizzaRestaurantBuilder pizzaRestaurantBuilder, int cooksCount, ICookingStrategy cookingStrategy, int checkoutCount) {
        pizzaRestaurantBuilder.setKitchen(new Kitchen());

        //куки
        CookList cookList=new CookList();
        cookList.setCookingStrategy(cookingStrategy);
        for(int i=0;i<cooksCount;++i){
            ICook cook=new Cook();
            cook.setId(i+1);
            cookList.addToList(cook);
        }
        pizzaRestaurantBuilder.getKitchen().setCookList(cookList);
        pizzaRestaurantBuilder.setCooks(cookList);


        //ресторансайз , пікпоінт
        RestaurantSize restaurantSize=new RestaurantSize(100,100);//наприкалд
        PickUpPoint pickUpPoint=new PickUpPoint();
        pizzaRestaurantBuilder.setRoomSize(restaurantSize);
        pizzaRestaurantBuilder.setPickUpPoint(pickUpPoint);

        //чекаути
        List<Checkout> ch=new ArrayList<>();
        for (int i=0;i<checkoutCount;++i){
            Checkout checkout=new Checkout(pizzaRestaurantBuilder.getKitchen(),pickUpPoint);
            checkout.setId(i+1);
            ch.add(checkout);
        }
        CheckoutList checkoutList=new CheckoutList(ch, pickUpPoint);
        pizzaRestaurantBuilder.setCheckoutList(checkoutList);



        for(var cook:CookList.getCooks()){
            cook.setPickUpPoint(checkoutList.getPickUpPoint());
        }




        //ордербоард, меню
        OrderBoard orderBoard=OrderBoard.getOrderBoard();
        pizzaRestaurantBuilder.setOrderBoard(orderBoard);
        Menu menu = new Menu();

        pizzaRestaurantBuilder.setMenu(menu);

        pizzaRestaurantBuilder.setMinPizzaTima(5);

    }
}
