package com.isep.gone.sixquiperd;

import com.isep.gone.sixquiperd.core.Game;
import com.isep.gone.sixquiperd.core.RoundState;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Scanner;

public class HelloApplication extends Application {

    private final Game game = new Game("Randomix", 1);
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        stage.show();
//        GridPane root = new GridPane();
//        Scene scene = new Scene(root, 640, 480);
//        scene.getStylesheets().add(Objects.requireNonNull(
//                getClass().getResource("hello.css")
//        ).toExternalForm(
//        ));
//
//        stage.setTitle("Formulaire");
//        // formulaire contenant nom, mot de passe, bouton de connexion
//
//        Button button = new Button("Connexion");
//        button.getStyleClass().add("my-button");
//        root.add(button, 1, 3);
//        TextField nom = new TextField("Nom");
//        root.add(nom, 1, 0);
//        PasswordField mdp = new PasswordField();
//        mdp.setPromptText("Mot de passe");
//        root.add(mdp, 1, 2);
//        TextField email = new TextField("Email");
//        root.add(email, 1, 1);
//
//
//        // make 2 radio buttons
//
//        RadioButton rb1 = new RadioButton("Student");
//        RadioButton rb2 = new RadioButton("Staff");
//
//        // make a toggle group and add both radio buttons to it
//        var group = new javafx.scene.control.ToggleGroup();
//        rb1.setToggleGroup(group);
//        rb2.setToggleGroup(group);
//
//        // add radio buttons to the grid pane
//        root.add(rb1, 0, 0);
//        root.add(rb2, 0, 1);
//
//
//        stage.setScene(scene);
//        stage.show();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                game.play();
                System.out.println("Score : " + game.getCurrentPlayer().getScore());
                System.out.println("Turn number " + game.getCurrentRound().getTurn());
                System.out.println("Current player is " + game.getCurrentPlayer().getName());
                System.out.println("Current state is " + game.getRoundState());
                if (game.getMainPlayer() == game.getCurrentPlayer()) {
                    System.out.println("It's your turn");
                    System.out.println("Your hand is : ");
                    for (int i = 0; i < game.getMainPlayer().getHand().size(); i++) {
                        System.out.println(i + " : " + game.getMainPlayer().getHand().get(i).getCardNumber());
                    }
                    if (game.getRoundState() == RoundState.WAITING_FOR_CARD || game.getRoundState() == RoundState.WAITING_FOR_ROW) {
                        System.out.println("Choose a card to play : ");
                        int cardIndex = scanner.nextInt();
                        scanner.nextLine();
                        if (game.getRoundState() == RoundState.WAITING_FOR_CARD) {
                            game.chooseCard(game.getCurrentPlayer().getHand().get(cardIndex));
                        } else {
                            System.out.println("Choose a row to play on : ");
                            int rowIndex = scanner.nextInt();
                            scanner.nextLine();
                            game.chooseRow(rowIndex);
                        }
                    }
                }
            }
        }.start();
    }


}
