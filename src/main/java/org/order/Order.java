package org.order;

import org.menu_and_pizza.AbstractProduct;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private int id;
    private List<AbstractProduct> products;
    private OrderStatus status;
    private IOrderBoardObserver orderBoard;

    public Order(int id, List<AbstractProduct> products) {
        this.id = id;
        this.products = new ArrayList<>(products);
        this.orderBoard = OrderBoard.getOrderBoard();
        this.status = OrderStatus.NEW;
    }
    public Order() {
        this.orderBoard = OrderBoard.getOrderBoard();
    }

    public void changeStatus(OrderStatus newOrderStatus){
        try {
            // Перевірка на null для newOrderStatus
            if (newOrderStatus != null) {
                status = newOrderStatus;
                notifyOrderBoard();
            } else {
                System.err.println("Спроба встановити null статус для замовлення.");
            }
        } catch (Exception e) {
            System.err.println("Помилка при зміні статусу замовлення: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public void notifyOrderBoard(){
        orderBoard.updateOrderStatus(id, status);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
       this.id=id;
    }

    public List<AbstractProduct> getProducts() {
        return products;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", products=" + products +
                ", status=" + status +
                '}';
    }
}
