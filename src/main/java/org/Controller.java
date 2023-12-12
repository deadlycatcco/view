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

    public static void setSimulation(Simulation sim) {
        simulation = sim;
    }
    public static void letsGo() {
        int amountOfCheckouts = helloApplication.getAmtCheck();
        simulation.setAmountOfCheckouts(amountOfCheckouts);

        // Execute GUI-related code on the JavaFX Application Thread
        Platform.runLater(() -> {
            // Create a new Stage object
            Stage primaryStage = new Stage();

            // Call start method with the new Stage object

           // simulation.runSimulation();
        });
    }

    public void setCustomerId(int customerId){

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
