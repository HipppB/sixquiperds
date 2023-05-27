package com.isep.gone.sixquiperd.ui;

// This class displays the menu of the game "Six Qui Prend"

import com.isep.gone.sixquiperd.core.Board;
import com.isep.gone.sixquiperd.core.Card;
import com.isep.gone.sixquiperd.core.Player;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Iterator;
import java.util.Objects;

public class BoardUi {
    // This method is called when the user clicks on the button
    // It changes the text of the label
    Stage primaryStage;


    BoardUi(Stage primaryStage) {
        this.primaryStage = primaryStage;

    }


    public void display(Board board, Player player) {

        System.out.println("BoardUi displayed");
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root, 640, 480);
        scene.getStylesheets().add(Objects.requireNonNull(
                getClass().getResource("board.css")
        ).toExternalForm(
        ));


        Label label = new Label("BoardUi displayed");
        label.getStyleClass().add("title");
        root.getChildren().add(label);

        // 4 line, 5 columns
        // Create a grid

        VBox vBox = new VBox();
        vBox.getStyleClass().add("vbox");
        System.out.println(board.getRows().size());
        for (int i = 0; i < board.getRows().size(); i++) {
            HBox hBox = new HBox();

            Iterator<Card> iterator = board.getRows().get(i).iterator();
            System.out.println(board.getRows().get(i).size());
            while (iterator.hasNext()) {
                System.out.println("hey");
                int cardNumber = iterator.next().getCardNumber();
                CardUi cardUi = new CardUi(cardNumber);
                ImageView imageView = cardUi.getCard();
                hBox.getChildren().add(imageView);

            }
            vBox.getChildren().add(hBox);

        }
        root.getChildren().add(vBox);


        primaryStage.setScene(scene);
    }

    // Create the method to set the board
    public void setBoard(Stage primaryStage) {

    }
}