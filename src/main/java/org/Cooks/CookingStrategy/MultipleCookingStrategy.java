package org.Cooks.CookingStrategy;

import org.Cooks.Cook;
import org.Cooks.CookingHandler.*;
import org.order.OrderStatus;

public class MultipleCookingStrategy implements ICookingStrategy {
    public void cook(Cook cook) {
        cook.getOrder().changeStatus(OrderStatus.PREPARING);
        BakeHandler bh = new BakeHandler();
        MakeDoughHandler dh = new MakeDoughHandler();
        MakeSauceHandler sh = new MakeSauceHandler();
        PackagingHandler ph = new PackagingHandler();
        PutToppingsHandler th = new PutToppingsHandler();
        dh.setCook(cook);
        dh.setNextHandler(sh);
//        sh.setNextHandler(th);
//        th.setNextHandler(bh);
//        bh.setNextHandler(ph);

    }
}
