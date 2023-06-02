package com.isep.gone.sixquiperd.ui;

import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

// This class listens to the user's actions on MenuUi
public class MenuController {
    // This method is called when the user clicks on the button
    // It changes the text of the label
    static void onEasyButtonClicked(Stage primaryStage, Parent root, int botNumber) {
        System.out.println("Easy button clicked");
        // Get textfield value
        String playerName = ((TextField) root.lookup("#playerName")).getText();

        if (!playerName.isEmpty()) {
            GameUiApplication.startGame(primaryStage, playerName, botNumber);
        } else {
            GameUiApplication.startGame(primaryStage, "Playerix", botNumber);
        }
    }

    static public void onMediumButtonClicked(Stage primaryStage) {
        System.out.println("Medium button clicked");
        //GameUiApplication.startGame(primaryStage);

        // TODO
    }

    static public void onHardButtonClicked(Stage primaryStage) {
        System.out.println(

                "Hard button clicked");
        //GameUiApplication.startGame(primaryStage);

        // TODO
    }
}
