package com.isep.gone.sixquiperd.ui;

import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

// This class listens to the user's actions on MenuUi
public class MenuController {
    private MenuController() {
    }

    // This method is called when the user clicks on the button
    // It changes the text of the label
    static void onEasyButtonClicked(Stage primaryStage, Parent root, int botNumber) {
        // Get textfield value
        String playerName = ((TextField) root.lookup("#playerName")).getText();

        if (!playerName.isEmpty()) {
            GameUiApplication.startGame(primaryStage, playerName, botNumber);
        } else {
            GameUiApplication.startGame(primaryStage, "Playerix", botNumber);
        }
    }

}
