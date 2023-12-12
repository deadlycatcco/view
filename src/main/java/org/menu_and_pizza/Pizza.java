package org.menu_and_pizza;

public class Pizza extends AbstractProduct{
    private PizzaCookingStage currentCookingStage;


    public Pizza(Pizza original) {

        super(original.getName(), original.getPrice());
        currentCookingStage = PizzaCookingStage.START;

    }
    public Pizza(){super();}
   public PizzaCookingStage whichStepToDo() {
       return currentCookingStage;
   }

    public  PizzaCookingStage getNextStage() {
        PizzaCookingStage[] values = PizzaCookingStage.values();
        int currentIndex = currentCookingStage.ordinal();

        if (currentIndex < values.length - 1) {
            currentCookingStage = values[currentIndex+1];
            return values[currentIndex + 1];
        } else {
            // If the current stage is the last one, return null or handle it as needed
            return null;
        }
    }
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

    public Pizza(String name, double price){
        super(name, price);
        currentCookingStage = PizzaCookingStage.START;
    }
    public void setStart() {
       currentCookingStage = PizzaCookingStage.START;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "currentCookingStage=" + currentCookingStage +
                ", name='" + name + '\'' +
                ", price=" + price +
                "} ";
    }
}
