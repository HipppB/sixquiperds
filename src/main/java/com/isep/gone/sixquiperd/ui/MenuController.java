package com.isep.gone.sixquiperd.ui;

import javafx.stage.Stage;

// This class listens to the user's actions on MenuUi
public class MenuController {
    // This method is called when the user clicks on the button
    // It changes the text of the label
    static void onEasyButtonClicked(Stage primaryStage) {
        System.out.println("Easy button clicked");
        GameUiApplication.startGame(primaryStage);
    }

    static public void onMediumButtonClicked(Stage primaryStage) {
        System.out.println("Medium button clicked");
        GameUiApplication.startGame(primaryStage);

        // TODO
    }

    static public void onHardButtonClicked(Stage primaryStage) {
        System.out.println(

                "Hard button clicked");
        GameUiApplication.startGame(primaryStage);

        // TODO
    }
}
