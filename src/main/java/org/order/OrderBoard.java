package org.order;

import org.Customer.ICustomerObserver;

import java.util.ArrayList;
import java.util.List;

public final class OrderBoard implements IOrderBoardObserver {

    private static OrderBoard orderBoard;
    public List<ICustomerObserver> customers;


    private OrderBoard() {
        // The following code emulates slow initialization.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.customers = new ArrayList<>();
    }


    public void addCustomer(ICustomerObserver customer) {
        try {
            // Перевірка на null перед додаванням
            if (customer != null) {
                customers.add(customer);
                System.out.println("Спостерігача успішно додано.");
            } else {
                System.err.println("Спроба додати null спостерігача.");
            }
        } catch (Exception e) {
            System.err.println("Помилка при додаванні спостерігача: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static OrderBoard getOrderBoard() {
        if (orderBoard == null) {
            orderBoard = new OrderBoard();
        }
        return orderBoard;
    }

    @Override
    public void updateOrderStatus(Integer id, OrderStatus status) {
          //оновити статут ордера в ордербоард, або видалити його якщо
        //order completed, або додати якщо його ще нема
        try {
            // Перевірка на null для id та status
            if (id != null && status != null) {
                // Оновлення статусу ордера в ордербоард
                System.out.println("OrderBoard: Ордер " + id + " змінив свій статус на " + status);

                // Перевірка на завершення замовлення
                if (status == OrderStatus.COMPLETED) {
                    notifyCustomer(id);
                }
            } else {
                System.err.println("Спроба оновлення статусу замовлення з null id або статусом.");
            }
        } catch (Exception e) {
            System.err.println("Помилка при оновленні статусу замовлення: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void notifyCustomer(int id){
        try {
            // Пошук за id
            ICustomerObserver customerToNotify = null;
            for (ICustomerObserver c : customers) {
                if (c.getId() == id) {
                    customerToNotify = c;
                    break; // Зупинка, оскільки знайдено відповідний спостерігач
                }
            }

            // Якщо не знайдено спостерігача з вказаним id
            if (customerToNotify != null) {
                customerToNotify.update(); // Кастомер йде по піцу
            } else {
                System.err.println("Спостерігача з id " + id + " не знайдено.");
            }
        } catch (Exception e) {
            System.err.println("Помилка при спробі сповістити клієнта: " + e.getMessage());
            e.printStackTrace();
        }

    }

}