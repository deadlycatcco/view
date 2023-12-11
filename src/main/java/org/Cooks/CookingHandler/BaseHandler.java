package org.Cooks.CookingHandler;

import org.Cooks.ICook;

public abstract class BaseHandler implements ICookingHandler{
    protected ICookingHandler nextHandler;
    protected ICook currentCook;
    public void setNextHandler(ICookingHandler h) {
        System.out.println(currentCook + " "+this);
        nextHandler = h;
        currentCook.nextCook().addOrder(currentCook.getOrder());
        nextHandler.setCook(currentCook.nextCook());
    }

    public void setCook(ICook cook) {
        currentCook = cook;
    }

    public void prepare() {
        nextHandler.prepare();
    }
}
