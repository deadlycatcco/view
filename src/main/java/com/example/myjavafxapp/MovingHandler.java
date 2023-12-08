package com.example.myjavafxapp;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.List;

public class MovingHandler {
    GridPane gridPane;

    private double cellWidth = 40;
    private double cellHeight = 40;

    public MovingHandler(GridPane gridPane){
        this.gridPane = gridPane;
    }

    public Pane createCell() {
        Pane cell = new Pane();
        cell.setMinSize(cellWidth, cellHeight); // Adjust the size of each cell
        cell.setStyle("-fx-border-color: white; -fx-border-width: 1;"); // Add border for visualization
        return cell;
    }

    public Pane getCell(GridPane gridPane, int row, int col) {
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

    public void moveBaseModelTo(GridPane gridPane, BaseModel baseModel, int targetRow, int targetCol) {
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

        transition.setOnFinished(event -> {
            Pane oldCell = getCell(gridPane, currentX, currentY);
            oldCell.getChildren().remove(baseModel.getGifImageView());

            Pane newCell = getCell(gridPane, targetRow, targetCol);
            newCell.getChildren().add(baseModel.getGifImageView());

            baseModel.getGifImageView().setTranslateX(0);
            baseModel.getGifImageView().setTranslateY(0);

            System.out.println("Thread " + Thread.currentThread().getName() + " finished moving baseModel to " +
                    targetRow + ", " + targetCol);

        });

    }



    void testfunc(List<CheckoutModel> checkoutModels, BaseModel baseModelTemp){
        int i = 0;
        int j = 1;
        Boolean ok = false;
        while (true) {
            if (isCellFree(gridPane, checkoutModels.get(0).getPositionX(),
                    checkoutModels.get(0).getPositionY() + j)) {
                int targetRow = checkoutModels.get(0).getPositionX();
                int targetCol = checkoutModels.get(0).getPositionY() + j;

                // Use Platform.runLater() to update UI from background thread
                moveBaseModelTo(gridPane, baseModelTemp, targetRow, targetCol);

                ok = true;
                break;  // Exit the loop after moving to a free cell
            } else {
                j++;
            }
            i++;
        }
    }
}
