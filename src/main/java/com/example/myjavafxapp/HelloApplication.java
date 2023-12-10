package com.example.myjavafxapp;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Thread.sleep;

public class HelloApplication extends Application {
    //private static final String IMAGE_URL = "https://images.squarespace-cdn.com/content/v1/551a19f8e4b0e8322a93850a/1566776697516-A69UYWW58V0871IQXG9C/Title_Image.png";
    //private static final String GIF_URL = "https://img.itch.zone/aW1nLzMzMzY4OTguZ2lm/original/0Ut41Y.gif";
    private static final String IMAGE_URL = "D:\\MyJavaFXApp\\src\\main\\resources\\Images\\cafe.png";
    private static final int GRID_ROWS = 14;
    private static final int GRID_COLUMNS = 20;
    private final Object monitor = new Object();

    private GridPane gridPane;
    private MovingHandler movingHandler = new MovingHandler(gridPane);

    private double cellWidth = 40; // Adjust the size of each cell
    private double cellHeight = 40; // Adjust the size of each cell


    @Override
    public void start(Stage primaryStage) throws InterruptedException {


        primaryStage.setTitle("BackgroundGridExample");

        Image backgroundImage = new Image(IMAGE_URL);
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

        // Add your cells to the grid here
        for (int row = 0; row < GRID_ROWS; row++) {
            for (int col = 0; col < GRID_COLUMNS; col++) {
                Pane cell = movingHandler.createCell();
                gridPane.add(cell, col, row);
            }
        }

        Pane cell1 = movingHandler.getCell(gridPane, 2,1);
        BaseModel baseModel1 = new BaseModel();
        // Set the fit width and fit height to be a fraction of the cell size
        double cellWidth1 = cell1.getMinWidth();
        double cellHeight1 = cell1.getMinHeight();
        baseModel1.getGifImageView().setFitWidth(cellWidth1 * 0.95); // Adjust the factor based on your preference
        baseModel1.getGifImageView().setFitHeight(cellHeight1 * 0.95);
        baseModel1.setPositionX(2);
        baseModel1.setPositionY(2);
        cell1.getChildren().add(baseModel1.getGifImageView());

        Pane cell = movingHandler.getCell(gridPane, 5,5);

        BaseModel baseModel = new BaseModel();
        // Set the fit width and fit height to be a fraction of the cell size
        double cellWidth = cell.getMinWidth();
        double cellHeight = cell.getMinHeight();
        baseModel.getGifImageView().setFitWidth(cellWidth * 0.95); // Adjust the factor based on your preference
        baseModel.getGifImageView().setFitHeight(cellHeight * 0.95);
        baseModel.setPositionX(5);
        baseModel.setPositionY(5);

        cell.getChildren().add(baseModel.getGifImageView());


//Каси
        int currentCheckout = 3;
        int checkoutsAmt = 5;
        List<CheckoutModel> checkoutModels = new ArrayList<>();

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

        //int currentCookingPoint = 3;
        int cookingPointsAmt = 5;
        List<CookPoint> cookPoints = new ArrayList<>();

        int positionCP = 2;
        for(int i = 1; i <= cookingPointsAmt; i++){
            CookPoint cookingPoint = new CookPoint();
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


//кастомери
        int AmountOfCustomers = 3;
        int tempAmount = AmountOfCustomers;
        int defaultRow = 6, defaultCol = 4;
        List<BaseModel> baseModels = new ArrayList<>();
        final int finalTempAmount = tempAmount;
        List<Integer> checkout0List = new ArrayList<>(Collections.nCopies(8, 1));

        checkout0List.set(0, 0);
        while (tempAmount != 0) {

            //Розміщую кастомера на дефолтну клітинку
            BaseModel baseModelTemp = new BaseModel();
            Pane customerCell1 =movingHandler.getCell(gridPane, defaultRow, defaultCol);
            baseModelTemp.getGifImageView().setFitWidth(cellWidth * 0.95); // Adjust the factor based on your preference
            baseModelTemp.getGifImageView().setFitHeight(cellHeight * 0.95);
            baseModelTemp.setPositionX(defaultRow);
            baseModelTemp.setPositionY(defaultCol);
            customerCell1.getChildren().add(baseModelTemp.getGifImageView());
            baseModels.add(baseModelTemp);

            Thread thread1 = new Thread(() -> {
                synchronized (monitor) {
                    Boolean ok = false;
                    int j = 1;
                    Thread.currentThread().setName("thread1");
                    System.out.println("Hello, I'm thread for baseModel 1 (" + (AmountOfCustomers - finalTempAmount) +
                            "), thread name: " + Thread.currentThread().getName());
                    for (int i = 0; i < checkout0List.size(); ++i) {
                        if(i == 0) continue;
                        if(checkout0List.get(i) == 0 ){
                            j++;
                        }
                        else {
                            checkout0List.set(j, 0);
                            break;}
                    }
                    final int j2 = j;
                    Platform.runLater(()->movingHandler.moveBaseModelTo(gridPane, baseModelTemp, checkoutModels.get(currentCheckout).getPositionX(),
                            checkoutModels.get(currentCheckout).getPositionY()+j2));
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            tempAmount--;
            thread1.start();
        }

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

        Scene scene = new Scene(root, backgroundImage.getWidth()+100, backgroundImage.getHeight());
        primaryStage.setScene(scene);
        primaryStage.setHeight(cellHeight * (GRID_ROWS+1));
        primaryStage.setWidth(cellWidth * (GRID_COLUMNS+0.5));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}





/*

    - перебір кас якщо заповнена, якщо всі заповнені то кастомери не генеруються
    - йде з каси через якийсь час в зону очікування
    - точки на кухні, рух куків по кухні
*/