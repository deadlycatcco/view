package org.Cooks.CookingStrategy;

import org.Cooks.Cook;
import org.Cooks.CookingHandler.BakeHandler;
import org.Cooks.CookingHandler.MakeDoughHandler;
import org.Cooks.CookingHandler.MakeSauceHandler;
import org.Cooks.CookingHandler.PackagingHandler;
import org.order.Order;
import org.order.OrderStatus;

public class MultipleCookingStrategy implements ICookingStrategy {
    public void cook(Cook cook) {
        Order order = cook.getOrder();
        if(order.getStatus()!=OrderStatus.PREPARING) {
            order.changeStatus(OrderStatus.PREPARING);

            BakeHandler bh = new BakeHandler();
            MakeDoughHandler dh = new MakeDoughHandler();
            MakeSauceHandler sh = new MakeSauceHandler();
            PackagingHandler ph = new PackagingHandler();
            bh.setCook(cook);
//        sh.setNextHandler(th);
//        th.setNextHandler(bh);
//        bh.setNextHandler(ph);
        }

    }
}
