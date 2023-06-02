package com.isep.gone.sixquiperd.ui;

import com.isep.gone.sixquiperd.core.Game;
import com.isep.gone.sixquiperd.core.Player;
import javafx.animation.AnimationTimer;


public class GameUiApplication extends javafx.application.Application {


    public static void main(String[] args) {
        launch(args);
    }

    public static void startGame(javafx.stage.Stage primaryStage, String playerName, int botNumber) {
        // Get TextField value, the text field is on the primary stage
        new AnimationTimer() {

            Game game = new Game(playerName, botNumber);

            BoardController boardController = new BoardController(game);
            BoardUi boardUi = new BoardUi(primaryStage, boardController);

            Player player = game.getMainPlayer();


            @Override
            public void handle(long now) {
                boardUi.display(game.getBoard(), player);
                if (game.getCurrentPlayer() != player) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }

                } else {
                    player.getCardToPlay();
                }
                game.play();

            }
        }.start();
    }

    @Override
    public void start(javafx.stage.Stage primaryStage) throws Exception {
        MenuUi menuUi = new MenuUi();
        menuUi.display(primaryStage);


    }


}
