package com.isep.gone.sixquiperd.ui;

// This class displays the menu of the game "Six Qui Prend"

import com.isep.gone.sixquiperd.core.Board;
import com.isep.gone.sixquiperd.core.Card;
import com.isep.gone.sixquiperd.core.Player;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Iterator;
import java.util.Objects;

public class BoardUi {
    // This method is called when the user clicks on the button
    // It changes the text of the label
    Stage primaryStage;
    Scene scene;
    AnchorPane root;

    BoardUi(Stage primaryStage) {
        this.root = new AnchorPane();
        this.primaryStage = primaryStage;
        this.scene = new Scene(this.root, 640 * 1.5, 480 * 1.5);
        initScene();
    }

    public void initScene() {
        this.primaryStage.setTitle("Six Qui Perd");

        scene.getStylesheets().add(Objects.requireNonNull(
                getClass().getResource("board.css")
        ).toExternalForm(
        ));
        this.primaryStage.setScene(this.scene);
        this.primaryStage.show();

    }

    public void display(Board board, Player player) {

        System.out.println("BoardUi displayed");
        VBox vBox = new VBox();
        Label label = new Label("BoardUi displayed");
        label.getStyleClass().add("title");
        vBox.getChildren().add(label);
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
                StackPane imageView = cardUi.getCard();
                hBox.getChildren().add(imageView);

            }
            vBox.getChildren().add(hBox);

        }

        System.out.println("Player hand");
        System.out.println(player.getHand().size());
        player.getHand().forEach(card -> {
            CardUi cardUi = new CardUi(card.getCardNumber());
            StackPane imageView = cardUi.getCard();
            vBox.getChildren().add(imageView);
        });
        root.getChildren().add(vBox);

    }

    public void refreshScene() {

    }

    // Create the method to set the board
    public void setBoard(Stage primaryStage) {

    }
}