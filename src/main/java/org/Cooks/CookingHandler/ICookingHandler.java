package org.Cooks.CookingHandler;

import org.Cooks.ICook;

public interface ICookingHandler {
    public void setNextHandler(ICookingHandler h);
    public void setCook(ICook cook);
    public void prepare();
}
