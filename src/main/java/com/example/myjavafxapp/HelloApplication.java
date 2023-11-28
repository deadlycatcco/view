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

public class HelloApplication extends Application {

    private static final String IMAGE_URL = "https://images.squarespace-cdn.com/content/v1/551a19f8e4b0e8322a93850a/1566776697516-A69UYWW58V0871IQXG9C/Title_Image.png";
    private static final String GIF_URL = "https://img.itch.zone/aW1nLzMzMzY4OTguZ2lm/original/0Ut41Y.gif";
    private static final int GRID_ROWS = 8;
    private static final int GRID_COLUMNS = 13;

    private GridPane gridPane;
    private double cellWidth = 70; // Adjust the size of each cell
    private double cellHeight = 70; // Adjust the size of each cell

    @Override
    public void start(Stage primaryStage) {
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
        Pane cell = getCell(gridPane, 0,0);
        Image gifImage = new Image(GIF_URL);
        ImageView gifImageView = new ImageView(gifImage);

        // Set the fit width and fit height to be a fraction of the cell size
        double cellWidth = cell.getMinWidth();
        double cellHeight = cell.getMinHeight();
        gifImageView.setFitWidth(cellWidth * 1.2); // Adjust the factor based on your preference
        gifImageView.setFitHeight(cellHeight * 1.2);

        cell.getChildren().add(gifImageView);
        moveGifToRight(cell, 0, 0);


//        for(int i = 0; i < 7; i++){
//            Pane targetCell = getCell(gridPane, i, i);
//            moveGif(cell, targetCell);
//        }


//        Button moveButton = new Button("Move GIF");
//        moveButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                moveGifFromCellToCell(cell, 1, 1);
//            }
//        });

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

    private void moveGifToRight(Pane sourceCell, int targetRow, int targetCol) {
        if (sourceCell.getChildren().size() > 0) {
            ImageView gifImageView = (ImageView) sourceCell.getChildren().get(0);

            // Create a new TranslateTransition
            TranslateTransition transition = new TranslateTransition(new Duration(10000), gifImageView);


            // Set the destination coordinates
            double targetX = getCell(gridPane, targetRow, targetCol).getLayoutX() + 1 * cellWidth;
            double targetY = getCell(gridPane, targetRow, targetCol).getLayoutY() + 0 * cellHeight;

            // Set the translation
            transition.setToX(targetX);
            transition.setToY(targetY);

            // Play the transition
            transition.play();

            // Remove the gif from the source cell
            sourceCell.getChildren().remove(gifImageView);

            // Add the gif to the target cell
            Pane targetCell = getCell(gridPane, targetRow, targetCol);
            targetCell.getChildren().add(gifImageView);
        }
    }

    private void moveGif(Pane sourceCell, Pane targetCell) {
        if (sourceCell.getChildren().size() > 0) {
            ImageView gifImageView = (ImageView) sourceCell.getChildren().get(0);

            // Set the initial position of the gif to the top-left corner of the source cell
            gifImageView.setLayoutX(0);
            gifImageView.setLayoutY(0);

            // Create a new TranslateTransition
            TranslateTransition transition = new TranslateTransition(new Duration(1000), gifImageView);

            // Set the destination position relative to the target cell
            transition.setToX(targetCell.getLayoutX() - sourceCell.getLayoutX());
            transition.setToY(targetCell.getLayoutY() - sourceCell.getLayoutY());

            // Play the transition
            transition.play();

            // Remove the gif from the source cell after the transition completes
            transition.setOnFinished(event -> {
                sourceCell.getChildren().remove(gifImageView);

                // Add the gif to the target cell
                targetCell.getChildren().add(gifImageView);
            });
        }
    }



    public static void main(String[] args) {
        launch(args);
    }


}
