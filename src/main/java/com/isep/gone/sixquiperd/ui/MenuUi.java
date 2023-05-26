package com.isep.gone.sixquiperd.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

// This class displays the menu of the game
public class MenuUi {
    public void display(Stage primaryStage) {
        // Launch main menu of the game with 3 buttons to choose the difficulty
        GridPane root = new GridPane();

        Scene scene = new Scene(root, 640, 480);
        scene.getStylesheets().add(Objects.requireNonNull(
                getClass().getResource("hello.css")
        ).toExternalForm(
        ));
        primaryStage.setTitle("Six Qui Perd");

        // Texts
        Text text = new Text("Six Qui Perd");
        Text instructions = new Text("Séléctionnez la difficulté");

        // Buttons
        Button buttonEasy = new Button("Easy");
        Button buttonMedium = new Button("Medium");
        Button buttonHard = new Button("Hard");


        buttonEasy.getStyleClass().add("my-button");
        buttonMedium.getStyleClass().add("my-button");
        buttonHard.getStyleClass().add("my-button");


        // Event listeners
        // Assign actions to the buttons using MenuController
        MenuController menuController = new MenuController();
        buttonEasy.setOnAction(event -> menuController.onEasyButtonClicked(primaryStage));
        buttonMedium.setOnAction(event -> menuController.onMediumButtonClicked(primaryStage));
        buttonHard.setOnAction(event -> menuController.onHardButtonClicked(primaryStage));

        // Add to root
        root.add(text, 1, 1);
        root.add(instructions, 1, 2);
        root.add(buttonEasy, 1, 3);
        root.add(buttonMedium, 1, 4);
        root.add(buttonHard, 1, 5);


        // Gap
        root.setHgap(10);
        root.setVgap(10);


        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println("GameUiApplication started");

    }

}
