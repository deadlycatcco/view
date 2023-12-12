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
    private ComboBox<Integer> comboBoxCook = new ComboBox<>(FXCollections.observableArrayList(1, 2, 3, 4, 5));

    private Stage stage;
    private static int checkoutAmount = 1; // Default value for K
    private static int cookAmount = 1;

    public static int getCheckoutAmount() {
        return checkoutAmount;
    }
    public static int getCookAmount() { return cookAmount; }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("Configuration");

        Label label = new Label("Select value for checkouts:");
        Button submitButton = new Button("Start Simulation");
        submitButton.setOnAction(e -> submitValue());

        Label label2 = new Label("Select value for cooks:");

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, comboBox);
        layout.getChildren().addAll(label2, comboBoxCook, submitButton);
        layout.setPadding(new Insets(10));

        Scene scene = new Scene(layout, 200, 150);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    private void submitValue() {
        // Get the selected values from the ComboBoxes
        Integer selectedValue = comboBox.getValue();
        Integer selectedValueCook = comboBoxCook.getValue();

        if (selectedValue != null) {
            // Store the values for later retrieval
            checkoutAmount = selectedValue;
            cookAmount = selectedValueCook;

            // Close the input window
            stage.close();
        }
    }
}