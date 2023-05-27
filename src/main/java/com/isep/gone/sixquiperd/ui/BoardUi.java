package com.isep.gone.sixquiperd.ui;

// This class displays the menu of the game "Six Qui Prend"

import com.isep.gone.sixquiperd.core.Board;
import com.isep.gone.sixquiperd.core.Card;
import com.isep.gone.sixquiperd.core.Player;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class BoardUi {
    // This method is called when the user clicks on the button
    // It changes the text of the label
    Stage primaryStage;
    Scene scene;
    Parent root;
    Integer selectedRow = null;
    Integer selectedHandCard = null;
    BoardController boardController;

    BoardUi(Stage primaryStage, BoardController boardController) {
        this.primaryStage = primaryStage;
        this.boardController = boardController;
        try {
            this.root = FXMLLoader.load(getClass().getResource("board3.fxml"));
            this.scene = new Scene(root);
            primaryStage.setScene(scene);
            // Set a title for the Stage
            primaryStage.setTitle("Six Qui Perd");
            // Display the Stage
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        initScene();
        setEventHandlers();
    }


    public void initScene() {
        this.primaryStage.setTitle("Six Qui Perd !");

        scene.getStylesheets().add(Objects.requireNonNull(
                getClass().getResource("board.css")
        ).toExternalForm(
        ));
        this.primaryStage.setScene(this.scene);
        this.primaryStage.show();
    }


    private void setEventHandlers() {
        // set event handlers on lines
        VBox mainBoard = (VBox) this.root.lookup("#boardMain");
        String selectorLine = "#line";
        for (int row = 0; row < mainBoard.getChildren().size(); row++) {
            HBox hBoxRow = (HBox) mainBoard.lookup(selectorLine + (row + 1));

            int finalRow = row;
            hBoxRow.setOnMouseClicked(event -> {
                this.selectedRow = finalRow;
                boardController.onRowClicked(finalRow);
                System.out.println("Clicked on row " + finalRow);
            });
        }

        // set event handlers on player hand
        HBox playerHand = (HBox) this.root.lookup("#playerHand");
        for (int i = 0; i < playerHand.getChildren().size(); i++) {
            Pane pane = (Pane) playerHand.getChildren().get(i);
            int finalI = i;
            pane.setOnMouseClicked(event -> {
                this.selectedHandCard = finalI;
                boardController.onCardHandClicked(finalI);
                System.out.println("Clicked on card Hand " + finalI);
            });
        }
        Button playButton = (Button) this.root.lookup("#playButton");
        playButton.setOnMouseClicked(event -> {
            System.out.println("Play button clicked " + this.selectedHandCard + " " + this.selectedRow);
            if (this.selectedRow != null && this.selectedHandCard != null) {
                System.out.println("Play card " + this.selectedHandCard + " on row " + this.selectedRow);

            }
        });
    }

    public void display(Board board, Player player) {
        displayBoard(board);
        displayPlayerHand(player);
        displayCardBeingChoosen(board);
        // id button #playButton
        Button playButton = (Button) this.root.lookup("#playButton");
        playButton.getStyleClass().add("disabled");

    }

    private void displayBoard(Board board) {
        VBox mainBoard = (VBox) this.root.lookup("#boardMain");

        // For each line in main board
        for (int i = 0; i < board.getRows().size(); i++) {
            HBox hBoxRow = (HBox) mainBoard.lookup("#line" + (i + 1));
            Iterator<Card> iterator = board.getRows().get(i).iterator();
            int j = 0;
            while (iterator.hasNext()) {
                int cardNumber = iterator.next().getCardNumber();
                CardUi cardUi = new CardUi(cardNumber);
                ((ImageView) hBoxRow.getChildren().get(j)).setImage(cardUi.getImage());
                j++;
            }
            if (j < 5) {
                for (int k = j; k < 5; k++) {
                    ((ImageView) hBoxRow.getChildren().get(k)).setImage(null);
                }
            }
        }
    }

    private void displayPlayerHand(Player player) {
        HBox playerHand = (HBox) this.root.lookup("#playerHand");
        List<Card> hand = player.getHand();
        for (int i = 0; i < hand.size(); i++) {
            CardUi cardUi = new CardUi(hand.get(i).getCardNumber());
            Pane pane = (Pane) playerHand.getChildren().get(i);
            ((ImageView) pane.getChildren().get(0)).setImage(cardUi.getImage());

        }
        if (hand.size() < 10) {
            for (int i = hand.size(); i < 10; i++) {
                Pane pane = (Pane) playerHand.getChildren().get(i);
                ((ImageView) pane.getChildren().get(0)).setImage(null);
            }
        }
    }

    private void displayCardBeingChoosen(Board board) {
        HBox cardContainer = (HBox) this.root.lookup("#CardBeeingChoosen");
        List<Card> cardsToReturn = board.getCardsToReturn();
        for (int i = 0; i < cardsToReturn.size(); i++) {
            CardUi cardUi = new CardUi(cardsToReturn.get(i).getCardNumber());
            Pane pane = (Pane) cardContainer.getChildren().get(i);
            ((ImageView) pane.getChildren().get(0)).setImage(cardUi.getImage());

        }
        if (cardsToReturn.size() < cardContainer.getChildren().size()) {
            for (int i = cardsToReturn.size(); i < cardContainer.getChildren().size(); i++) {
                Pane pane = (Pane) cardContainer.getChildren().get(i);
                ((ImageView) pane.getChildren().get(0)).setImage(null);
            }
        }

    }


}