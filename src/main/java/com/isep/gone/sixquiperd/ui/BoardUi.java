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
            Parent root = FXMLLoader.load(getClass().getResource("board3.fxml"));
            this.root = root;
            System.out.println(root);
            Scene scene = new Scene(root);
            this.scene = scene;
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
        for (int row = 0; row < mainBoard.getChildren().size(); row++) {
            HBox hBoxRow = (HBox) mainBoard.lookup("#line" + (row + 1));

            int finalRow = row;
            hBoxRow.setOnMouseClicked(event -> {
                for (int j = 0; j < mainBoard.getChildren().size(); j++) {
                    HBox hBoxRow1 = (HBox) mainBoard.lookup("#line" + (j + 1));
                    if (hBoxRow1.getStyleClass().contains("selected")) {
                        hBoxRow1.getStyleClass().remove("selected");
                    }
                }
                hBoxRow.getStyleClass().add("selected");
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

                // remove selected class from all cards
//                for (int j = 0; j < playerHand.getChildren().size(); j++) {
//                    Pane pane1 = (Pane) playerHand.getChildren().get(j);
//                    if (pane1.getStyleClass().contains("selectedCard")) {
//                        pane1.getStyleClass().remove("selectedCard");
//                    }
//                }
                this.selectedHandCard = finalI;
//                playerHand.getChildren().get(finalI)
//                        .getStyleClass().add("selectedCard");
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

        // For each player card

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

        // id button #playButton
        Button playButton = (Button) this.root.lookup("#playButton");

        if (playButton.getStyleClass().contains("disabled") && player.getCardToPlay() != null) {
            playButton.getStyleClass().remove("disabled");
        } else if (!playButton.getStyleClass().contains("disabled") && player.getCardToPlay() == null) {
            playButton.getStyleClass().add("disabled");
        }


//
//        VBox vBox = new VBox();
//        vBox.getStyleClass().add("vbox");
//
//
//        for (int i = 0; i < board.getRows().size(); i++) {
//            HBox hBox = new HBox();
//            Iterator<Card> iterator = board.getRows().get(i).iterator();
//            while (iterator.hasNext()) {
//                int cardNumber = iterator.next().getCardNumber();
//                CardUi cardUi = new CardUi(cardNumber);
//                hBox.getChildren().add(cardUi.getCard());
//
//            }
//            vBox.getChildren().add(hBox);
//        }
//
//        HBox PlayerHand = new HBox();
//        PlayerHand.setMouseTransparent(true);
//        vBox.setMargin(PlayerHand, new javafx.geometry.Insets(10, 10, 10, 10));
//        vBox.setMouseTransparent(true);
//
//
//        player.getHand().forEach(card -> {
//            CardUi cardUi = new CardUi(card.getCardNumber());
//            Pane pane = cardUi.getCard();
//            pane.getStyleClass().add("card");
//            pane.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
//                System.out.println("clicked on " + card.getCardNumber());
//            });
//            PlayerHand.getChildren().add(pane);
//        });
//
//        Label playerName = new Label(player.getName());
//        playerName.getStyleClass().add("playerName");
//        vBox.getChildren().add(playerName);
//        vBox.getChildren().add(PlayerHand);
//
//        AnchorPane anchorPane = new AnchorPane();
//        anchorPane.getChildren().add(vBox);
////        root.getChildren().clear();
////        root.getChildren().add(anchorPane);

    }

    private HBox choosenCards(List<Card> Cards) {
        HBox hBox = new HBox();
        Cards.forEach(card -> {
            CardUi cardUi = new CardUi(card.getCardNumber());
            hBox.getChildren().add(cardUi.getCard());
        });
        return hBox;
    }


}