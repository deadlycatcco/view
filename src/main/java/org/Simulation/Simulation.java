package org.Simulation;

import org.Checkout.Checkout;
import org.Checkout.CheckoutList;
import org.Checkout.PickUpPoint;
import org.Controller;
import org.Cooks.Cook;
import org.Cooks.CookList;
import org.Cooks.CookingStrategy.ICookingStrategy;
import org.Cooks.CookingStrategy.MultipleCookingStrategy;
import org.Cooks.CookingStrategy.SingleCookingStrategy;
import org.Cooks.ICook;
import org.Customer.Customer;
import org.Customer.CustomerGenerator;
import org.Customer.CustomerManager;
import org.Customer.ICustomerObserver;

import org.PizzaRestaurant.*;
import org.menu_and_pizza.Menu;

import org.menu_and_pizza.AbstractProduct;
import org.menu_and_pizza.Pizza;
import org.order.Order;
import org.order.OrderBoard;
import org.order.OrderStatus;
import org.order.generation.MultiplePizzaAndBeverageStrategy;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Lock;

public class Simulation {
    private PizzaRestaurant pizzaRestaurant;
    private static int amountOfCheckouts = 0;
    private static int amountOfCooks = 0;
    private static int minPizzaTime = 0;
    private static String chosenStrategy = "";
    private static final Object lock2 = new Object();
    public PizzaRestaurant init() {
        PizzaRestaurantBuilder builder = new PizzaRestaurantBuilder();
    //    PizzaRestaurantDirector director = new PizzaRestaurantDirector(builder);
    //    pizzaRestaurant = director.construct();

        // Додаткові дії для ініціалізації, якщо потрібно

        return pizzaRestaurant;
    }
    public void runSimulation() {
        if (pizzaRestaurant == null) {
            System.out.println("Ресторан не ініціалізовано. Викличте метод init() перед запуском симуляції.");
            return;
        }

        // логіка симуляції
        System.out.println("Симуляція ресторану запущена...");
        // додаткові дії для симуляції
    }

    public void setAmountOfCheckouts(int amtOfCheckouts){amountOfCheckouts = amtOfCheckouts;}
    public void setAmountOfCooks(int amtOfCooks){amountOfCooks = amtOfCooks;}
    public void setChosenStrategy(String strategy){chosenStrategy = strategy;}
    public void setMinTime(int minTime){minPizzaTime = minTime;}
    public static void YULIIA_TEST_CODE(){

        /*System.out.println("Hello, i`m pizza restaurant");

//ордербоард сповіщає кастомера щоб ордер готовий
        IOrderBoardObserver orderObserver=OrderBoard.getOrderBoard();
        ICustomerObserver customer1=new Customer();
        ICustomerObserver customer2=new Customer();
        customer1.setId(1);
        customer2.setId(2);
        orderObserver.addCustomer(customer1);
        orderObserver.addCustomer(customer2);

        //ордери сповіщають ордербоард про зміну свого статусу
        Order order1=new Order();
        Order order2=new Order();
        Order order3=new Order();
        order1.setId(1);
        order2.setId(2);
        order3.setId(3);

        order1.changeStatus(OrderStatus.NEW);
        order2.changeStatus(OrderStatus.COMPLETED);
        order3.changeStatus(OrderStatus.PREPARING);*/



      /*  ICustomerObserver customer1=new Customer();
        ICustomerObserver customer2=new Customer();

        checkout1.customers.add(customer1);
        checkout1.customers.add(customer1);
        checkout1.customers.add(customer1);
        checkout2.customers.add(customer1);

        checkout3.customers.add(customer1);
        checkout3.customers.add(customer1);
        checkout3.customers.add(customer1);
        checkout3.customers.add(customer1);
        checkout3.customers.add(customer1);
        checkout3.customers.add(customer1);*/

        //тут перевірка чи генеруються кастомери і чи вибирають найменшу чергу
//        Checkout checkout1=new Checkout();
//        checkout1.setId(1);
//        Checkout checkout2=new Checkout();
//        checkout2.setId(2);
//        Checkout checkout3=new Checkout();
//        checkout3.setId(3);
//
//
//        CheckoutList ch=new CheckoutList();
//        ch.addToList(checkout1);
//        ch.addToList(checkout2);
//        ch.addToList(checkout3);
//
//        CustomerManager customerManager=new CustomerManager(ch);
//
//        for(int i=0;i<5;++i)
//        customerManager.sendCustomerToCheckout();


    }


    public static void TEST_ASSIGN_ORDER(){
        Menu menu = new Menu();

        Kitchen kitchen=new Kitchen();
//
        CookList cookList=new CookList();
        ICookingStrategy cookingStrategy=new SingleCookingStrategy();

        cookList.setCookingStrategy(cookingStrategy);

        ICook cook1=new Cook();
        //cook1.setStrategy(new SingleCookingStrategy());
        ICook cook2=new Cook();
       //cook2.setStrategy(new SingleCookingStrategy());
        ICook cook3=new Cook();
        //cook3.setStrategy(cookingStrategy);
        cookList.addToList(cook1);
        cookList.addToList(cook2);


        kitchen.setCookList(cookList);
        List<AbstractProduct> str1 = new MultiplePizzaAndBeverageStrategy().generate();
        List<AbstractProduct> str2 = new MultiplePizzaAndBeverageStrategy().generate();
        List<AbstractProduct> str3 = new MultiplePizzaAndBeverageStrategy().generate();
        Order order1=new Order(1,str1);
        Order order2=new Order(2,str2);
        Order order3=new Order(3,str3);


        Queue<Order> orderQueue=new LinkedList<Order>();
        orderQueue.add(order2);
        orderQueue.add(order3);
        orderQueue.add(order1);
        kitchen.setOrderQueue(orderQueue);

        kitchen.assignOrder();

    }

    public static void OlesiaTest() {
        Menu menu = new Menu();
        CustomerGenerator customerGenerator = CustomerGenerator.getInstance();
        for(int i = 0; i< 20; i++) {
            System.out.println(customerGenerator.generateCustomer());
        }

    }
    private static final Object lock = new Object();
    static  public void TEST_START_PROGRAM(){
        Controller controller = new Controller();
        ICookingStrategy cookingStrategy = null;
        if (chosenStrategy == "Single Strategy"){
            cookingStrategy=new SingleCookingStrategy();
        }else if(chosenStrategy == "Multiple Strategy"){
            cookingStrategy=new MultipleCookingStrategy();
        }
        PizzaRestaurantDirector director=new PizzaRestaurantDirector();
        PizzaRestaurantBuilder builder=new PizzaRestaurantBuilder();
        director.construct(builder, amountOfCooks, cookingStrategy,amountOfCheckouts, minPizzaTime);
        controller.createCooks();
        PizzaRestaurant pizzaRestaurant=builder.getResult();

        pizzaRestaurant.getCheckoutList().runAllCheckoutThread();


        CustomerGenerator customerGenerator = CustomerGenerator.getInstance();
        List<Customer> customers=new ArrayList<>();
//        pizzaRestaurant.getOrderBoard().getOrderBoardView().start();
        CustomerManager customerManager=new CustomerManager(pizzaRestaurant.getCheckoutList());
        Thread CustomerGenerator = new Thread(()->{
            synchronized (lock2) {
                while(true) {
                if(pizzaRestaurant.getCheckoutList().getCheckouts().get(2).getCustomersCount()<=2) {                Customer customer = customerGenerator.generateCustomer();
                    System.out.println(customer);
                    customers.add(customer);
                    pizzaRestaurant.getOrderBoard().addCustomer(customer);
                    int checkoutId = customerManager.sendCustomerToCheckout(customer);
                    System.out.println("CHECKOUT BEST " + checkoutId);
                    controller.setCustomerIdAndCheckout(customer.getId(), checkoutId);
                }
                try {
                    lock2.wait(500);
                }catch (Exception ex){}
            }        }
        }) ;
        CustomerGenerator.start();      //кастомер менеджер

        pizzaRestaurant.getKitchen().assignOrder();







    }


    public static void main(String[] args) throws InterruptedException {

   //    YULIIA_TEST_CODE();
      //  ILLIATEST();
    //   TEST_ASSIGN_ORDER();
      //  OlesiaTest();
        TEST_START_PROGRAM();

    }
    public static void ILLIATEST() {
       /* Kitchen k = new Kitchen();
        CookList cooklist = new CookList();
        cooklist.setCookingStrategy(new MultipleCookingStrategy());
        Cook cook1 = new Cook();
        Cook cook2 = new Cook();
        cooklist.addToList(cook1);
        cooklist.addToList(cook2);
        Order order = new Order();
        Order order2 = new Order();
        Order order3 = new Order();
        AbstractProduct pizza1 = new Pizza("Pizza1",500);
        AbstractProduct pizza2 = new Pizza("Pizza2",500);
        AbstractProduct pizza3 = new Pizza("Pizza3",500);
        AbstractProduct pizza4 = new Pizza("Pizza4",500);

        List<AbstractProduct> pizzas = new ArrayList<>();
        List<AbstractProduct> pizzas2 = new ArrayList<>();
        List<AbstractProduct> pizzas3 = new ArrayList<>();
        pizzas.add(pizza1);
        pizzas.add(pizza2);
        pizzas2.add(pizza3);
        pizzas3.add(pizza4);
        order.setProducts(pizzas);
        order2.setProducts(pizzas2);
        order3.setProducts(pizzas3);
        Queue<IOrder> orderQueue = new LinkedList<>();
        orderQueue.add(order);
        orderQueue.add(order2);
        orderQueue.add(order3);
        k.setOrderQueue(orderQueue);
        k.setCookList(cooklist);
        k.assignOrder();*/

    }

}