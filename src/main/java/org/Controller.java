package org;

import com.example.myjavafxapp.HelloApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.Simulation.Simulation;

public class Controller {
    private static HelloApplication helloApplication;
    private static Simulation simulation;

    public static void setHelloApplication(HelloApplication helloApp) {
        helloApplication = helloApp;
    }
    public void setAmountOfCheckout(int amountOfCheckout){
        simulation.setAmountOfCheckouts(amountOfCheckout);
    }
    public void setAmountOfCooks(int amountOfCooks){simulation.setAmountOfCooks(amountOfCooks);}

    public static void setSimulation(Simulation sim) {
        simulation = sim;
    }
    public static void letsGo() {
        int amountOfCheckouts = helloApplication.getAmtCheck();
        simulation.setAmountOfCheckouts(amountOfCheckouts);
        int amountOfCooks = helloApplication.getAmtCooks();
        simulation.setAmountOfCooks(amountOfCooks);

        // Execute GUI-related code on the JavaFX Application Thread
        Platform.runLater(() -> {
            // Create a new Stage object
            Stage primaryStage = new Stage();

            // Call start method with the new Stage object

           // simulation.runSimulation();
        });
    }

    public void deleteCustomer(int customerId, int checkoutId){
        helloApplication.deleteCustomerFromView(customerId-1,checkoutId-1);
    }

    public void setCustomerIdAndCheckout(int customerId, int checkoutId){
        helloApplication.CreateCustomer(customerId-1,checkoutId-1);
    }

    public void setStrategy(String strategy){
        simulation.setChosenStrategy(strategy);
    }

    public void setMinPizzaTime(int minTime){
        simulation.setMinTime(minTime);
    }

    public void createCooks(){
       helloApplication.CreateCook();
    }
    public void movetowait(int customerId, int checkoutId){
        helloApplication.moveToWaitZone(customerId, checkoutId);
    }

    public void startSimulation(){
        Simulation.TEST_START_PROGRAM();
    }

//    public static void main(String[] args) {
////        HelloApplication helloApplication1 = new HelloApplication();
////        Simulation simulation1 = new Simulation();
////        simulation1.init();
////        simulation1.runSimulation();
////        Controller controller = new Controller(helloApplication1, simulation1);
////        Application.launch(HelloApplication.class, args);
////        controller.letsGo();
//        Application.launch(HelloApplication.class, args);
//    }
}
