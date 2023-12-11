package com.example.myjavafxapp;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InputDialog extends Application {
    private ComboBox<Integer> comboBox = new ComboBox<>(FXCollections.observableArrayList(1, 2, 3, 4, 5));
    private Stage stage;
    private static int checkoutAmount;

    public static int getCheckoutAmount() {
        return checkoutAmount;
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("Configuration");

        Label label = new Label("Select value for K:");
        Button submitButton = new Button("Start Simulation");
        submitButton.setOnAction(e -> submitValue());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, comboBox, submitButton);
        layout.setPadding(new Insets(10));

        Scene scene = new Scene(layout, 200, 150);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    private void submitValue() {
        // Get the selected value from the ComboBox
        Integer selectedValue = comboBox.getValue();

        if (selectedValue != null) {
            // Store the value for later retrieval
            checkoutAmount = selectedValue;

            // Close the input window
            stage.close();
        }
    }
}