package com.isep.gone.sixquiperd.ui;

// This class displays the menu of the game "Six Qui Prend"

import com.isep.gone.sixquiperd.core.Board;
import com.isep.gone.sixquiperd.core.Card;
import com.isep.gone.sixquiperd.core.Player;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    BoardController boardController;

    BoardUi(Stage primaryStage, BoardController boardController) {
        this.primaryStage = primaryStage;
        this.boardController = boardController;
        try {
            this.root = FXMLLoader.load(getClass().getResource("board3.fxml"));
            this.scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Six Qui Perd");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        initScene();
        setEventHandlers();
    }

    public void initScene() {
        primaryStage.setScene(scene);
        primaryStage.setHeight(552);
        primaryStage.setWidth(704);
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.show();
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
            hBoxRow.setOnMouseClicked(event ->
                    boardController.onRowClicked(finalRow)
            );
        }

        // set event handlers on player hand
        HBox playerHand = (HBox) this.root.lookup("#playerHand");
        for (int i = 0; i < playerHand.getChildren().size(); i++) {
            Pane pane = (Pane) playerHand.getChildren().get(i);
            int finalI = i;
            pane.setOnMouseClicked(event ->
                    boardController.onCardHandClicked(finalI)
            );
        }
        Button playButton = (Button) this.root.lookup("#playButton");
        playButton.setOpacity(0);
    }

    public void display(Board board, Player player) {
        displayBoard(board);
        displayPlayerHand(player);
        displayCardBeingChoosen(board);
        displayScore();
        // id button #playButton


    }

    private void displayScore() {
        VBox scoreBoard = (VBox) this.root.lookup("#scoreBoard");
        scoreBoard.getChildren().clear();
        List<Player> players = boardController.getPlayers();
        for (int i = 0; i < players.size(); i++) {
            Label label = new Label(players.get(i).getScore() + " " + players.get(i).getName());
            label.getStyleClass().add("player");
            if (players.get(i) == boardController.getCurrentPlayer()) {
                label.getStyleClass().add("currentPlayer");
            }
            label.getStyle();

            scoreBoard.getChildren().add(label);
        }
    }

    private void displayBoard(Board board) {
        VBox mainBoard = (VBox) this.root.lookup("#boardMain");

        // For each line in main board
        for (int i = 0; i < board.getRows().size(); i++) {
            HBox hBoxRow = (HBox) mainBoard.lookup("#line" + (i + 1));
            Iterator<Card> iterator = board.getRows().get(i).iterator();
            int j = 0;
            while (iterator.hasNext()) {
                ((ImageView) hBoxRow.getChildren().get(j)).setImage(ICard.getImage(iterator.next()));
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
            Pane pane = (Pane) playerHand.getChildren().get(i);
            ((ImageView) pane.getChildren().get(0)).setImage(ICard.getImage(hand.get(i)));

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
            Pane pane = (Pane) cardContainer.getChildren().get(i);
            ((ImageView) pane.getChildren().get(0)).setImage(ICard.getImage(cardsToReturn.get(i)));

        }
        if (cardsToReturn.size() < cardContainer.getChildren().size()) {
            for (int i = cardsToReturn.size(); i < cardContainer.getChildren().size(); i++) {
                Pane pane = (Pane) cardContainer.getChildren().get(i);
                ((ImageView) pane.getChildren().get(0)).setImage(null);
            }
        }

    }


}