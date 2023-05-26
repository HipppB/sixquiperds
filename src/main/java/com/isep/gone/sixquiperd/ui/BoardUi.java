package com.isep.gone.sixquiperd.ui;

// This class displays the menu of the game "Six Qui Prend"

import com.isep.gone.sixquiperd.core.Board;
import com.isep.gone.sixquiperd.core.Player;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BoardUi {
    // This method is called when the user clicks on the button
    // It changes the text of the label
    Stage primaryStage;

    BoardUi(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void display(Board board, Player player) {
        System.out.println("BoardUi displayed");
        // 4 line, 5 columns
        // Create a grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        // Add elements to the grid
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                Text text = new Text("Cell " + i + "," + j);
                StackPane stackPane = new StackPane();
                stackPane.getChildren().add(text);
                grid.add(stackPane, j, i);
            }
        }

        Scene scene = new Scene(grid, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Create the method to set the board
    public void setBoard(Stage primaryStage) {

    }
}