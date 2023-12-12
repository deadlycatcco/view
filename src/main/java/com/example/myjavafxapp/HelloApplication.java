package com.example.myjavafxapp;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

import org.Controller;
import org.Simulation.Simulation;
import static java.lang.Thread.sleep;

public class HelloApplication extends Application {

    private final CountDownLatch customerMoveLatch = new CountDownLatch(1);
    //private static final String IMAGE_URL = "https://images.squarespace-cdn.com/content/v1/551a19f8e4b0e8322a93850a/1566776697516-A69UYWW58V0871IQXG9C/Title_Image.png";
    //private static final String GIF_URL = "https://img.itch.zone/aW1nLzMzMzY4OTguZ2lm/original/0Ut41Y.gif";
    private static final String IMAGE_URL = "/Images/cafe.png";
    private static final int GRID_ROWS = 14;
    private static final int GRID_COLUMNS = 20;
    private Controller controller = new Controller();

    private final Object monitor = new Object();

    private GridPane gridPane;
    private MovingHandler movingHandler = new MovingHandler(gridPane);

    private double cellWidth = 40; // Adjust the size of each cell
    private double cellHeight = 40; // Adjust the size of each cell
    List<CheckoutModel> checkoutModels;
    List<List<BaseModel>> baseModels;
    List<List<Integer>> checkoutList;
    //private static int checkoutsAmt;

//    public static int getCheckoutsAmt() {
//        return checkoutsAmt;
//    }
    private int checkAmount = 0;
    @Override
    public void start(Stage primaryStage) {
        // Show InputDialog to get K
        int k = -1;
        InputDialog inputDialog = new InputDialog();

        while (k < 1 || k > 5) {
            Optional<Integer> result = showInputDialog();

            if (result.isPresent()) {
                try {
                    k = result.get();

                    if (k <  1 || k > 5) {
                        // Show an alert for invalid input
                        showAlert("Invalid Input", "K should be between 1 and 5");
                    }
                } catch (NumberFormatException e) {
                    // Show an alert for invalid number format
                    showAlert("Invalid Input", "Please enter a valid number");
                }
            } else {
                // User canceled the input, exit the application
                System.exit(0);
            }
        }

        // Continue with your program using the value of k
        try {
            checkAmount = k;
             setupPrimaryStage(primaryStage, k);
            controller.setAmountOfCheckout(k);
            System.out.println("Received K: " + k);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        controller.startSimulation();
    }


    public void setupPrimaryStage(Stage primaryStage, int kValue) throws InterruptedException {
        primaryStage.setTitle("PizzaRestaurant");

        Image backgroundImage = new Image(getClass().getResource(IMAGE_URL).toExternalForm());
        BackgroundImage background = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        gridPane = new GridPane();
        gridPane.setHgap(0);
        gridPane.setVgap(0);
        gridPane.setBackground(new Background(background)); // Встановлення фону для GridPane

        ImageView imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imageView.fitWidthProperty().bind(primaryStage.widthProperty());
        imageView.fitHeightProperty().bind(primaryStage.heightProperty());

        StackPane stackPane = new StackPane(imageView, gridPane);
        stackPane.setStyle("-fx-background-color: red;");

//        openNewWindow();

       // InputDialog inputDialog = new InputDialog();
//        inputDialog.start(new Stage());
//
//    // Retrieve the value of checkoutsAmt from InputDialog
//    checkoutsAmt = InputDialog.getCheckoutAmount();

        int checkoutsAmt = kValue;

        // Add your cells to the grid here
        for (int row = 0; row < GRID_ROWS; row++) {
            for (int col = 0; col < GRID_COLUMNS; col++) {
                Pane cell = movingHandler.createCell();
                gridPane.add(cell, col, row);
            }
        }

//Каси
        int currentCheckout = 0;
       // int checkoutsAmt = 5;
         checkoutModels = new ArrayList<>();

        int position = 2;
        for(int i = 1; i <= checkoutsAmt; i++){
            CheckoutModel checkoutModel = new CheckoutModel();
            checkoutModel.setPositionX(position);
            checkoutModel.setPositionY(0);
            checkoutModels.add(checkoutModel);
            Pane checkoutCell = movingHandler.getCell(gridPane, position,0);
            double cellCheckoutWidth = checkoutCell.getMinWidth();
            double cellCheckoutHeight = checkoutCell.getMinHeight();
            checkoutModel.getGifImageView().setFitWidth(cellCheckoutWidth * 0.95); // Adjust the factor based on your preference
            checkoutModel.getGifImageView().setFitHeight(cellCheckoutHeight * 0.95);

            checkoutCell.getChildren().add(checkoutModel.getGifImageView());
            position+= 2;
        }
//Пойнти
        //int currentCookingPoint = 3;
        int cookingPointsAmt = 5;
        List<CookPoint> cookPoints = new ArrayList<>();

        int positionCP = 2;
        for(int i = 1; i <= cookingPointsAmt; i++){
            CookPoint cookingPoint = new CookPoint();
            String imageUrl = "/Images/cookingPoint_%d.png";
            cookingPoint.setGif_url(imageUrl, i);
            System.out.println(imageUrl);
            cookingPoint.setPositionX(positionCP);
            cookingPoint.setPositionY(gridPane.getColumnCount()-1);
            cookPoints.add(cookingPoint);
            Pane cookPointCell = movingHandler.getCell(gridPane, positionCP,gridPane.getColumnCount()-1);
            double cellCheckoutWidth = cookPointCell.getMinWidth();
            double cellCheckoutHeight = cookPointCell.getMinHeight();
            cookingPoint.getGifImageView().setFitWidth(cellCheckoutWidth * 0.95); // Adjust the factor based on your preference
            cookingPoint.getGifImageView().setFitHeight(cellCheckoutHeight * 0.95);

            cookPointCell.getChildren().add(cookingPoint.getGifImageView());
            positionCP+= 2;
        }


        int AmountOfCooks = 3;
        int tempAmountOfCooks = AmountOfCooks;
        int defaultRow = 1, defaultCol = 15;
        List<CookModel> cookModels = new ArrayList<>();
        final int finalTempAmount = tempAmountOfCooks;

        List<Integer> cookPointsStatus = new ArrayList<>();
        for(int i = 0; i < cookingPointsAmt; i++){
            cookPointsStatus.add(0);
        }

        while (tempAmountOfCooks != 0) {

            CookModel cookModelTemp = new CookModel();
            Pane cookCell1 = movingHandler.getCell(gridPane, defaultRow, defaultCol);
            cookModelTemp.getGifImageView().setFitWidth(cellWidth * 0.95);
            cookModelTemp.getGifImageView().setFitHeight(cellHeight * 0.95);
            cookModelTemp.setPositionX(defaultRow);
            cookModelTemp.setPositionY(defaultCol);
            cookCell1.getChildren().add(cookModelTemp.getGifImageView());
            cookModels.add(cookModelTemp);

            Thread thread1 = new Thread(() -> {
               // synchronized (this) {
                    Boolean ok = false;
                     int j = 1;
                    Thread.currentThread().setName("thread1");
                    System.out.println("Hello, I'm thread for baseModel 1 (" + (AmountOfCooks - finalTempAmount) +
                            "), thread name: " + Thread.currentThread().getName());
                    //synchronized (this) {
                        while (true) {

                            int cu = cookModelTemp.currentPoint;
                            synchronized (this) {
                            if (cookPointsStatus.get(cu) != 1) {

                                    if (cu != 0) {
                                        synchronized (monitor) {
                                            cookPointsStatus.set(cu - 1, 0);
                                        }
                                    }
                                    Platform.runLater(() -> movingHandler.moveCookModelTo(gridPane, cookModelTemp, cookPoints.get(cu).getPositionX(),
                                            cookPoints.get(cu).getPositionY() - 2));
                                    cookModelTemp.currentPoint++;
                                    cookPointsStatus.set(cu, 1);
                                } else{
                                    continue;
                                }
                            }

                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }

                            if (cookModelTemp.currentPoint > 4) {
                                cookPointsStatus.set(cu, 0);
//                        Platform.runLater(()->movingHandler.moveCookModelTo(gridPane, cookModelTemp, cookPoints.get(cu).getPositionX(),
//                                cookPoints.get(cu).getPositionY()-2));
                                Platform.runLater(() -> movingHandler.moveCookModelTo(gridPane, cookModelTemp, defaultRow,
                                        defaultCol));
                                break;
                            }
                        }
                    //}
            });
            tempAmountOfCooks--;

            thread1.start();
        }

//кастомери
        int AmountOfCustomers = 3;
        Integer tempAmount = AmountOfCustomers;
        int defaultCustRow = 6;
        int defaultCustCol = 4;
         baseModels = new ArrayList<>();
         for(int i = 0; i < checkAmount; i++){
             baseModels.add(new ArrayList<>());
         }
        final int finalCustTempAmount = tempAmount;
         checkoutList = new ArrayList<>();
         for(int i = 0; i < checkAmount; i++){
             checkoutList.add(new ArrayList<>(Collections.nCopies(8, 1)));
             checkoutList.get(i).set(0,0);
         }


//        while (tempAmount != 0) {
//
//            createCustomerOnView(tempAmount, checkoutModels.get(currentCheckout), baseModels.get(currentCheckout), checkoutList.get(currentCheckout));
//            tempAmount--;
//        }

//це щоб генерувати куків один біля одного в залежності від кількості куків, але це все дуже приблизно

        //        int amountOfCook = 6;
        //        List<BaseModel> baseModels = new ArrayList<>();
        //        for(int i = 0; i < amountOfCook; i++){
        //            baseModels.add(new BaseModel());
        //        }
        //        int c = 1;
        //        for (BaseModel baseModelTemp: baseModels) {
        //            Pane cellll = getCell(gridPane, 0,c);
        //            baseModelTemp.setPositionX(0);
        //            baseModelTemp.setPositionY(c);
        //            baseModelTemp.getGifImageView().setFitWidth(cellWidth1 * 0.95);
        //            baseModelTemp.getGifImageView().setFitHeight(cellHeight1 * 0.95);
        //            cellll.getChildren().add(baseModelTemp.getGifImageView());
        //            c++;
        //        }

        //VBox vBox = new VBox(stackPane, moveButton);
        BorderPane root = new BorderPane();
        root.setCenter(stackPane);

        Scene scene = new Scene(root, backgroundImage.getWidth(), backgroundImage.getHeight());
        primaryStage.setScene(scene);
        primaryStage.setHeight(cellHeight * (GRID_ROWS+1));
        primaryStage.setWidth(cellWidth * (GRID_COLUMNS+0.5));
        primaryStage.show();

        Controller.setHelloApplication(this);
        Controller.setSimulation(new Simulation());

        Controller.letsGo();
    }

//    public static void setCheckoutAmount(int checkoutAmount) {
//        checkoutsAmt = checkoutAmount;
//        // Handle the checkoutAmount value as needed
//        System.out.println("Received checkoutAmount: " + checkoutAmount);
//    }

    public static void main(String[] args) {
        launch(args);
//        Simulation.TEST_START_PROGRAM();
//       Application.launch(HelloApplication.class, args);
    }

    public int getAmtCheck(){
        return checkAmount;
    }

    public void CreateCustomer(int customerId, int checkoutId){
        createCustomerOnView(customerId, checkoutModels.get(checkoutId), baseModels.get(checkoutId),checkoutList.get(checkoutId));
       // moveToWaitZone(customerId, baseModels.get(checkoutId).get(customerId));
    }

    public void createCustomerOnView(Integer customerId, CheckoutModel checkoutModel,
                                      List<BaseModel> baseModels, List<Integer> checkoutList) {

        int defaultCustRow = 13;
        int defaultCustCol = 10;
        BaseModel baseModelTemp = new BaseModel();
        baseModelTemp.setId(customerId);
        Pane customerCell1 = movingHandler.getCell(gridPane, defaultCustRow, defaultCustCol);
        baseModelTemp.getGifImageView().setFitWidth(cellWidth * 0.95); // Adjust the factor based on your preference
        baseModelTemp.getGifImageView().setFitHeight(cellHeight * 0.95);
        baseModelTemp.setPositionX(defaultCustRow);
        baseModelTemp.setPositionY(defaultCustCol);
        Platform.runLater(() -> {
            customerCell1.getChildren().add(baseModelTemp.getGifImageView());
            baseModels.add(baseModelTemp);
        });

        Thread thread2 = new Thread(() -> {

            Boolean ok = false;
            int j = 1;
            Thread.currentThread().setName("thread1");
            System.out.println("Hello, I'm thread for baseModel 1 (" +
                    "), thread name: " + Thread.currentThread().getName());
            synchronized (this) {
                for (int i = 0; i < checkoutList.size(); ++i) {
                    if (i == 0) continue;
                    if (checkoutList.get(i) == 0) {
                        j++;
                    } else {
                        checkoutList.set(j, 0);
                        break;
                    }
                }

            }
            final int j2 = j;
            Platform.runLater(() -> {
                movingHandler.moveBaseModelTo(gridPane, baseModelTemp, checkoutModel.getPositionX(),
                        checkoutModel.getPositionY() + j2);
                //customerMoveLatch.countDown();
            });
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });
        thread2.start();
    }

////////////////////////////////////////////////////////////////////////////


 public void moveToWaitZone(Integer customerId, int checkoutId){
         Thread thread3 = new Thread(() -> {
             try {
                 Thread.sleep(3000);
             } catch (InterruptedException e) {
                 throw new RuntimeException(e);
             }
             Boolean ok = false;
             BaseModel customer;
             Thread.currentThread().setName("thread3");
             System.out.println("Hello, I'm thread for customer (" + customerId +
                     "), thread name: " + Thread.currentThread().getName());
                 customer = baseModels.get(checkoutId).get(0);
             int [] newPosition = findFreeCellInWaitzone(gridPane, 2, 9, 13, 13);
             //Pane customerCell1 = movingHandler.getCell(gridPane, newPosition[0], newPosition[1]);
             //customerCell1.getChildren().add(new ImageView());
             Platform.runLater(() -> {
                 movingHandler.moveBaseModelTo(gridPane, customer, newPosition[0], newPosition[1]);
             });
         });
         thread3.start();
 }

 private void moveWhenFree(){
        
 }

 private int[] findFreeCellInWaitzone(GridPane gridPane, int startRow, int startCol, int endRow, int endCol){
     for (int row = startRow; row <= endRow; row++) {
         for (int col = startCol; col <= endCol; col++) {
             if (movingHandler.isCellFree(gridPane, row, col)) {
                 return new int[]{row, col};
             }
         }
     }
     // No free cell found in the specified range
     return null;
 }


////////////////////////////////////////////////////////////////////////////
    private Optional<Integer> showInputDialog() {
        Dialog<Integer> dialog = new Dialog<>();
        dialog.setTitle("Input Dialog");
        dialog.setHeaderText("Enter K:");

        // Set the icon (if you have one)
        // dialog.setGraphic(new ImageView(this.getClass().getResource("icon.png").toString()));

        // Set the button types
        ButtonType enterButtonType = new ButtonType("Enter", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(enterButtonType, ButtonType.CANCEL);

        // Create the K label and field
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField kField = new TextField();
        kField.setPromptText("K");

        grid.add(new Label("K:"), 0, 0);
        grid.add(kField, 1, 0);

        // Enable/Disable login button depending on whether a K was entered
        Node enterButton = dialog.getDialogPane().lookupButton(enterButtonType);
        enterButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax)
        kField.textProperty().addListener((observable, oldValue, newValue) -> {
            enterButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the K field by default
        Platform.runLater(kField::requestFocus);

        // Convert the result to a K value when the login button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == enterButtonType) {
                return Integer.parseInt(kField.getText());
            }
            return null;
        });

        return dialog.showAndWait();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}





/*

    - перебір кас якщо заповнена, якщо всі заповнені то кастомери не генеруються
    - йде з каси через якийсь час в зону очікування
    - точки на кухні, рух куків по кухні
*/