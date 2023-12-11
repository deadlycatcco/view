package org.menu_and_pizza;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private static List<Pizza> pizzas;
    private static List<Beverage> beverages;

    public Menu() {
        pizzas = new ArrayList<>();

        pizzas.add(new Pizza("Margherita", 9.99));
        pizzas.add(new Pizza("Pepperoni", 11.99));

        beverages = new ArrayList<>();

        beverages.add(new Beverage("Cola", 2.49));
        beverages.add(new Beverage("Lemonade", 2.99));
    }

    public Menu(List<Pizza> pizzasList, List<Beverage> beveragesList) {
        pizzas = pizzasList;
        beverages = beveragesList;
    }

    public static List<Pizza> getPizzas() {
        return pizzas;
    }

    public static List<Beverage> getBeverages() {
        return beverages;
    }

}
