package org.Cooks;

import org.Cooks.CookingStrategy.ICookingStrategy;

import java.util.ArrayList;

public class CookList {
    private static ArrayList<ICook> cooks = null;
    private ICookingStrategy cookingStrategy;
    public CookList(ArrayList<ICook> c) {
        cooks = new ArrayList<ICook>();
        for (var cook : c) {
            this.addToList(cook);
        }

    }
    public CookList() {
        cooks = new ArrayList<ICook>();
    }

    public void addToList(ICook Cook){
        Cook.setStrategy(cookingStrategy);
        this.cooks.add(Cook);
        ThreadCook threadCook = new ThreadCook((Cook)Cook);
        Thread a = new Thread(threadCook);
        a.start();
    }

    public void removeFromList(ICook Cook) {
        if(!cooks.isEmpty())
            this.cooks.remove(Cook);
    }

    public static ICook findFirstFree() {
        for(var cook : cooks) {
            if(cook.isFree()) {
                return cook;
            }
        }
        return null;
    }

    public void createCockThreads(){

    }
    public static ArrayList<ICook> getCooks() {
        return cooks;
    }
    public boolean isEmpty() {

        return  cooks.isEmpty();
    }

    public void setCookingStrategy(ICookingStrategy cookingStrategy) {
        this.cookingStrategy = cookingStrategy;
    }
}
