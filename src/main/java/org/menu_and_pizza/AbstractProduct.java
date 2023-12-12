package org.menu_and_pizza;

public abstract class AbstractProduct {
    protected String name;
    protected double price;

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
    public AbstractProduct(){}

    public AbstractProduct(String name, double price){
        this.name = name;
        this.price = price;
    }
}
