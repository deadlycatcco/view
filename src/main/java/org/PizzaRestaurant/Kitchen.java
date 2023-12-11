package org.PizzaRestaurant;
import org.Cooks.Cook;
import org.Cooks.ICook;
import org.Cooks.CookList;
import org.order.Order;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Kitchen {
    private Queue<Order> orderQueue;
    private CookList cookList;

    // Конструктор
    public Kitchen() {
        orderQueue = new LinkedList<>();
        cookList = new CookList();
    }
    private final Object lock = new Object();
    public void assignOrder() {

        System.out.println("size" +orderQueue.size());
        Thread thread1 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                   if (!orderQueue.isEmpty() && !cookList.isEmpty()) {
                        ICook cook = null;
                       if(cookList.findFirstFree()!=null){
                            cook=cookList.findFirstFree();
                            Order order = (Order) orderQueue.poll();
                            System.out.println("кук "+cook.hashCode()+" взяв ордер "+order.getId());
                            cook.addOrder(order);
                        }
                   }
                }
                try {

                    Thread.sleep(500); // Зупинка на пів секунди (500 мілісекунд)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
       /* if (!orderQueue.isEmpty() && !cookList.isEmpty()) {
            Order order = orderQueue.poll();
            ICook cook = null;
            while (cook==null) {
                try {
                    synchronized (this) {
                        cook = cookList.findFirstFree();
                        this.wait(1000);
                    }
                }catch (InterruptedException e) {
                    return;
                }
            }
            cook.addOrder(order);
        }*/
    }
    public CookList getCookList() {
        return cookList;
    }

    public void setCookList(CookList cookList) {
        this.cookList = cookList;
    }
    public Queue<Order> getOrderQueue() {
        return orderQueue;
    }

    public void setOrderQueue(Queue<Order> orderQueue) {
        this.orderQueue = orderQueue;
    }

    public void addOrderToQueue(Order order){
        orderQueue.add(order);
    }
}
