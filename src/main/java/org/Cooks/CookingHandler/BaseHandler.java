package org.Cooks.CookingHandler;

import org.Cooks.Cook;
import org.Cooks.ICook;

public abstract class BaseHandler implements ICookingHandler{
    protected ICookingHandler nextHandler;
    protected ICook currentCook;
    public void setNextHandler(ICookingHandler h) {
        nextHandler = h;
        ICook cook = currentCook.nextCook();
        currentCook.reGiveOrder((Cook)cook);
        nextHandler.setCook(cook);
    }

    public void setCook(ICook cook) {
        currentCook = cook;
    }

    public void prepare() {
        nextHandler.prepare();
    }
}
