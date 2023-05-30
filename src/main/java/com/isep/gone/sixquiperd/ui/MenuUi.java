package com.isep.gone.sixquiperd.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
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
        
        MenuController menuController = new MenuController();

        playButton.setOnMouseClicked(event -> {
            menuController.onEasyButtonClicked(primaryStage);

        });


        // Texts
//        Text text = new Text("Six Qui Perd");
//        Text instructions = new Text("Séléctionnez la difficulté");
//
//        // Buttons
//        Button buttonEasy = new Button("Easy");
//        Button buttonMedium = new Button("Medium");
//        Button buttonHard = new Button("Hard");
//
//
//        buttonEasy.getStyleClass().add("my-button");
//        buttonMedium.getStyleClass().add("my-button");
//        buttonHard.getStyleClass().add("my-button");


        // Event listeners
        // Assign actions to the buttons using MenuController
//        MenuController menuController = new MenuController();
//        buttonEasy.setOnAction(event -> menuController.onEasyButtonClicked(primaryStage));
//        buttonMedium.setOnAction(event -> menuController.onMediumButtonClicked(primaryStage));
//        buttonHard.setOnAction(event -> menuController.onHardButtonClicked(primaryStage));

        // Add to root
//        root.add(text, 1, 1);
//        root.add(instructions, 1, 2);
//        root.add(buttonEasy, 1, 3);
//        root.add(buttonMedium, 1, 4);
//        root.add(buttonHard, 1, 5);


        // Gap
//        root.setHgap(10);
//        root.setVgap(10);
//
//
//        primaryStage.setScene(scene);
//        primaryStage.show();
//        System.out.println("GameUiApplication started");

    }

}
