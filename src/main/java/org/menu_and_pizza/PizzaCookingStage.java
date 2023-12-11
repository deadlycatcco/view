package org.menu_and_pizza;

public enum PizzaCookingStage {
    START("Start"),
    DOUGH("Dough"),
    SAUCE("Sauce"),
    TOPPINGS("Toppings"),
    BAKED("Backed"),
    PACKAGING("Packaging");

    private String name;

    PizzaCookingStage(String name) {
        this.name = name;
    }

    public String getStageName() {
        return name;
    }
}