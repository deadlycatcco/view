package com.example.myjavafxapp;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class OrderBoardView {
    public TextArea textArea;
    public void start(){
        Stage newStage = new Stage();

        // Set up the content for the new stage with TextArea
        textArea = new TextArea();
        textArea.setEditable(false); // Optional: Set to true if you want to allow user input


        StackPane newLayout = new StackPane(textArea);
        Scene newScene = new Scene(newLayout, 500, 500);
        newStage.setScene(newScene);
        newStage.setTitle("Order Board");

        // Show the new stage
        newStage.show();

    }
}