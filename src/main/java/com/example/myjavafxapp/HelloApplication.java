package com.example.myjavafxapp;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class HelloApplication extends Application {

    private static final String IMAGE_URL = "https://images.squarespace-cdn.com/content/v1/551a19f8e4b0e8322a93850a/1566776697516-A69UYWW58V0871IQXG9C/Title_Image.png";
    private static final String GIF_URL = "https://img.itch.zone/aW1nLzMzMzY4OTguZ2lm/original/0Ut41Y.gif";
    private static final int GRID_ROWS = 8;
    private static final int GRID_COLUMNS = 13;

    private GridPane gridPane;
    private double cellWidth = 70; // Adjust the size of each cell
    private double cellHeight = 70; // Adjust the size of each cell

    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        primaryStage.setTitle("BackgroundGridExample");

        Image backgroundImage = new Image(IMAGE_URL);
        gridPane = new GridPane();
        gridPane.setHgap(0);
        gridPane.setVgap(0);
        gridPane.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        ImageView imageView = new ImageView(backgroundImage);
        imageView.setPreserveRatio(true);
        imageView.fitWidthProperty().bind(primaryStage.widthProperty());
        imageView.fitHeightProperty().bind(primaryStage.heightProperty());

        StackPane stackPane = new StackPane(imageView, gridPane);
        stackPane.setStyle("-fx-background-color: black;");

        // Add your cells to the grid here
        for (int row = 0; row < GRID_ROWS; row++) {
            for (int col = 0; col < GRID_COLUMNS; col++) {
                Pane cell = createCell();
                gridPane.add(cell, col, row);
            }
        }

        Pane cell1 = getCell(gridPane, 2,2);
        BaseModel baseModel1 = new BaseModel();
        // Set the fit width and fit height to be a fraction of the cell size
        double cellWidth1 = cell1.getMinWidth();
        double cellHeight1 = cell1.getMinHeight();
        baseModel1.getGifImageView().setFitWidth(cellWidth1 * 0.95); // Adjust the factor based on your preference
        baseModel1.getGifImageView().setFitHeight(cellHeight1 * 0.95);
        baseModel1.setPositionX(2);
        baseModel1.setPositionY(2);
        cell1.getChildren().add(baseModel1.getGifImageView());

        Pane cell = getCell(gridPane, 5,5);

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
        int checkoutsAmt = 7;
        List<CheckoutModel> checkoutModels = new ArrayList<>();

        for(int i = 1; i <= checkoutsAmt; i++){
            CheckoutModel checkoutModel = new CheckoutModel();
            checkoutModel.setPositionX(0);
            checkoutModel.setPositionY(i);
            checkoutModels.add(checkoutModel);
            Pane checkoutCell = getCell(gridPane, 0,i);
            double cellCheckoutWidth = checkoutCell.getMinWidth();
            double cellCheckoutHeight = checkoutCell.getMinHeight();
            checkoutModel.getGifImageView().setFitWidth(cellCheckoutWidth * 0.95); // Adjust the factor based on your preference
            checkoutModel.getGifImageView().setFitHeight(cellCheckoutHeight * 0.95);

            checkoutCell.getChildren().add(checkoutModel.getGifImageView());
        }

        int AmountOfCustomers = 3;
        int tempAmount = AmountOfCustomers;
        int defaultRow = 6, defaultCol = 4;
        List<BaseModel> baseModels = new ArrayList<>();
        while(tempAmount != 0){

            //Розміщую кастомера на дефолтну клітинку
            BaseModel baseModelTemp = new BaseModel();
            Pane customerCell1 = getCell(gridPane, defaultRow,defaultCol);
            baseModelTemp.getGifImageView().setFitWidth(cellWidth * 0.95); // Adjust the factor based on your preference
            baseModelTemp.getGifImageView().setFitHeight(cellHeight * 0.95);
            baseModelTemp.setPositionX(defaultRow);
            baseModelTemp.setPositionY(defaultCol);
            customerCell1.getChildren().add(baseModelTemp.getGifImageView());
            baseModels.add(baseModelTemp);

            int i = 0;
            int j = 1;
            Boolean ok = false;
            // Тепер перебираю каси
            while(true)
            {
                if(isCellFree(gridPane, checkoutModels.get(0).getPositionX()+j,
                        checkoutModels.get(0).getPositionY())){
                    moveBaseModelTo(gridPane,baseModelTemp, checkoutModels.get(0).getPositionX()+j,
                            checkoutModels.get(0).getPositionY());
                    ok = true;
                }
                else {
                    j++;
                }
                i++;
                if(ok){
                    break;
                }

            }


            tempAmount--;
            break;
        }




////це щоб в залежності від координат челік рухався кудись, і якщо клітинка зайнята
//// щоб він ставав на клітинку нижче для кастомерів
//        int targetRow = 1;
//        int targetCol = 1;
//        cell.getChildren().add(baseModel.getGifImageView());
//        System.out.println(isCellFree(gridPane, targetRow,targetCol));
//
//        if (isCellFree(gridPane, targetRow,targetCol)){
//            moveBaseModelTo(gridPane,baseModel, targetRow,targetCol);
//        } else {
//            moveBaseModelTo(gridPane,baseModel, targetRow+1,targetCol);
//        }
//



        //moveGifToRight(cell, 0, 0);

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


//        moveBaseModel(gridPane, baseModel);
//        moveBaseModel(gridPane, baseModel);
//        moveBaseModel(gridPane, baseModel);
//        moveBaseModel(gridPane, baseModel);




        //VBox vBox = new VBox(stackPane, moveButton);
        BorderPane root = new BorderPane();
        root.setCenter(stackPane);

        Scene scene = new Scene(root, backgroundImage.getWidth()+100, backgroundImage.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Pane createCell() {
        Pane cell = new Pane();
        cell.setMinSize(70, 70); // Adjust the size of each cell
        cell.setStyle("-fx-border-color: white; -fx-border-width: 1;"); // Add border for visualization
        return cell;
    }

    private Pane getCell(GridPane gridPane, int row, int col) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
                if (node instanceof Pane) {
                    return (Pane) node;
                }
            }
        }
        return null;
    }

    public boolean isCellFree(GridPane gridPane, int targetRow, int targetCol){
        Pane cell = getCell(gridPane, targetRow, targetCol);
        if(!cell.getChildren().isEmpty()){
                return false;
        }
        return true;
    }
    public void moveBaseModel(GridPane gridPane, BaseModel baseModel) {
        int currentX = baseModel.getPositionX();
        int currentY = baseModel.getPositionY();

        // Search for an available cell around the current position
        for (int offsetX = -1; offsetX <= 1; offsetX++) {
            for (int offsetY = -1; offsetY <= 1; offsetY++) {
                int targetRow = currentX + offsetX;
                int targetCol = currentY + offsetY;

                if (isCellFree(gridPane, targetRow, targetCol)) {
                    moveBaseModelTo(gridPane, baseModel, targetRow, targetCol);
                    return; // Move only once to the first available cell
                }
            }
        }
    }

   private void moveBaseModelTo(GridPane gridPane, BaseModel baseModel, int targetRow, int targetCol) {
        int currentX = baseModel.getPositionX();
        int currentY = baseModel.getPositionY();

        double cellWidth = this.cellWidth;
        double cellHeight = this.cellHeight;

        double newTranslateX;
        double newTranslateY;
        if(currentX > targetRow)  {
             newTranslateX = (targetCol - currentY) * cellWidth;
        }else {
            newTranslateX = (currentY - targetCol) * -cellWidth;
        }
        if(currentY < targetCol) {
            newTranslateY = (targetRow - currentX) * cellHeight;
        }else {
            newTranslateY = (currentX - targetRow) *-cellHeight;
        }


        TranslateTransition transition = new TranslateTransition(Duration.seconds(3), baseModel.getGifImageView());
        transition.setToX(newTranslateX);
        transition.setToY(newTranslateY);
        transition.play();

        baseModel.setPositionX(targetRow);
        baseModel.setPositionY(targetCol);

//       Pane oldCell = getCell(gridPane, currentX, currentY);
//       oldCell.getChildren().remove(baseModel.getGifImageView());
//
//       Pane newCell = getCell(gridPane, targetRow, targetCol);
//       newCell.getChildren().add(baseModel.getGifImageView());
        transition.setOnFinished(event -> {
            Pane oldCell = getCell(gridPane, currentX, currentY);
            oldCell.getChildren().remove(baseModel.getGifImageView());

            Pane newCell = getCell(gridPane, targetRow, targetCol);
            newCell.getChildren().add(baseModel.getGifImageView());

            baseModel.getGifImageView().setTranslateX(0);
            baseModel.getGifImageView().setTranslateY(0);

        });

    }
//    public void moveBaseModel(GridPane gridPane, BaseModel baseModel, int targetRow, int targetCol) {
//        // Отримуємо поточні координати клітинки
//        int currentX = baseModel.getPositionX();
//        int currentY = baseModel.getPositionY();
//
//        // Отримуємо розмір клітинки
//        double cellWidth = this.cellWidth;
//        double cellHeight = this.cellHeight;
//
//        // Оновлюємо розташування gifImageView в новій клітинці
//        double newTranslateX;
//        double newTranslateY;
//        if (baseModel.getPositionX() > targetRow){
//             newTranslateX = (baseModel.getPositionX() -targetRow) * -cellWidth;
//        }else {
//             newTranslateX = (targetRow - baseModel.getPositionX()) * cellWidth;
//        }
//        if (baseModel.getPositionY() > targetCol){
//             newTranslateY = (baseModel.getPositionY()- targetCol) * -cellHeight;
//        }else {
//             newTranslateY = (targetCol - baseModel.getPositionY()) * cellHeight;
//        }
////        newTranslateX = -targetRow * cellWidth;
////        newTranslateY = -targetCol * cellHeight;
//        // Створюємо TranslateTransition для анімації переміщення
//         TranslateTransition transition = new TranslateTransition(Duration.seconds(5), baseModel.getGifImageView());
//        transition.setToX(newTranslateX);
//        transition.setToY(newTranslateY);
//        transition.play();
//
//        // Змінюємо координати базової моделі
//        baseModel.setPositionX(targetRow);
//        baseModel.setPositionY(targetCol);
//
//        // Видаляємо gifImageView зі старої клітинки після завершення анімації
//        transition.setOnFinished(event -> {
//            Pane oldCell = getCell(gridPane, currentX, currentY);
//            oldCell.getChildren().remove(baseModel.getGifImageView());
//
//            // Додаємо gifImageView до нової клітинки
//            Pane newCell = getCell(gridPane, targetRow, targetCol);
//            newCell.getChildren().add(baseModel.getGifImageView());
//
//            // Очищаємо трансформації, щоб уникнути зайвих зсувів
//            baseModel.getGifImageView().setTranslateX(0);
//            baseModel.getGifImageView().setTranslateY(0);
//        });
//    }

//
//    private void moveGifToRight(Pane sourceCell, int targetRow, int targetCol) {
//        if (sourceCell.getChildren().size() > 0) {
//            ImageView gifImageView = (ImageView) sourceCell.getChildren().get(0);
//
//            // Create a new TranslateTransition
//            TranslateTransition transition = new TranslateTransition(new Duration(10000), gifImageView);
//
//
//            // Set the destination coordinates
//            double targetX = getCell(gridPane, targetRow, targetCol).getLayoutX() + 1 * cellWidth;
//            double targetY = getCell(gridPane, targetRow, targetCol).getLayoutY() + 2 * cellHeight;
//
//            // Set the translation
//            transition.setToX(targetX);
//            transition.setToY(targetY);
//
//            // Play the transition
//            transition.play();
//
//            // Remove the gif from the source cell
//            sourceCell.getChildren().remove(gifImageView);
//
//            // Add the gif to the target cell
//            Pane targetCell = getCell(gridPane, targetRow, targetCol);
//            targetCell.getChildren().add(gifImageView);
//        }
//    }
//
//    private void moveGif(Pane sourceCell, Pane targetCell) {
//        if (sourceCell.getChildren().size() > 0) {
//            ImageView gifImageView = (ImageView) sourceCell.getChildren().get(0);
//
//            // Set the initial position of the gif to the top-left corner of the source cell
//            gifImageView.setLayoutX(0);
//            gifImageView.setLayoutY(0);
//
//            // Create a new TranslateTransition
//            TranslateTransition transition = new TranslateTransition(new Duration(1000), gifImageView);
//
//            // Set the destination position relative to the target cell
//            transition.setToX(targetCell.getLayoutX() - sourceCell.getLayoutX());
//            transition.setToY(targetCell.getLayoutY() - sourceCell.getLayoutY());
//
//            // Play the transition
//            transition.play();
//
//            // Remove the gif from the source cell after the transition completes
//            transition.setOnFinished(event -> {
//                sourceCell.getChildren().remove(gifImageView);
//
//                // Add the gif to the target cell
//                targetCell.getChildren().add(gifImageView);
//            });
//        }
//    }
//
//

    public static void main(String[] args) {
        launch(args);
    }


}
