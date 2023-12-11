package org.order;

import org.Customer.ICustomerObserver;

public interface IOrderBoardObserver {
    public void updateOrderStatus(Integer id, OrderStatus status);
    public void notifyCustomer(int id);
    public void addCustomer(ICustomerObserver c);
}
