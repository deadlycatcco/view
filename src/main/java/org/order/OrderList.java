package org.order;

import java.util.ArrayList;

public class OrderList {
    private ArrayList<Order> orderQueue;

    public void addToList(Order order){
        orderQueue.add(order);
    }
    public void removeFromList(Order order){
        orderQueue.remove(order);
    }



    //yura
    public boolean isEmptyOrderQueue()
    {
        return orderQueue.isEmpty();
    }
    public void removeFromListById(int id)
    {
        orderQueue.removeIf(x->x.getId()==id);
    }
    public boolean containsOrder(Order order)
    {
        return orderQueue.contains(order);
    }
}
