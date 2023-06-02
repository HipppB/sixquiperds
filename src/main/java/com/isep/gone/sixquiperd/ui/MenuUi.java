package com.isep.gone.sixquiperd.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

// This class displays the menu of the game
public class MenuUi {
    public void display(Stage primaryStage) {

        Parent root = new GridPane();
        try {
            root = FXMLLoader.load(getClass().getResource("menu.fxml"));
            root.getStylesheets().add(Objects.requireNonNull(
                    getClass().getResource("board.css")
            ).toExternalForm(
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Launch main menu of the game with 3 buttons to choose the difficulty

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        // Set a title for the Stage
        primaryStage.setTitle("Six Qui Perd");
        // Display the Stage
        primaryStage.show();
        Button playButton = (Button) root.lookup("#playButton");
        playButton.getStyleClass().add("button");

        Text botLabel = (Text) root.lookup("#botNumber");
        Slider botSlider = (Slider) root.lookup("#botSlider");

        Parent finalRoot = root;
        playButton.setOnMouseClicked(event ->
                MenuController.onEasyButtonClicked(primaryStage, finalRoot, (int) botSlider.getValue())
        );


        botSlider.setOnMouseReleased(event ->
                botLabel.setText(String.valueOf((int) botSlider.getValue()) + " bots")
        );


    }

}
