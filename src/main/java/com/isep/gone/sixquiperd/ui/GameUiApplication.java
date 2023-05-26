package com.isep.gone.sixquiperd.ui;

import com.isep.gone.sixquiperd.core.Game;
import com.isep.gone.sixquiperd.core.Player;
import javafx.animation.AnimationTimer;

public class GameUiApplication extends javafx.application.Application {

    public static void main(String[] args) {
        launch(args);
    }

    public static void startGame(javafx.stage.Stage primaryStage) {
        System.out.println("Game started");
        new AnimationTimer() {
            Game game = new Game("Super Player", 4);
            Player player = game.getPlayer();
            BoardUi boardUi = new BoardUi(primaryStage);

            @Override
            public void handle(long now) {
                boardUi.display(game.getBoard(), game.getCurrentPlayer());
                stop();

//                board = game.getBoard();
//                player = game.getPlayer();
//
//                if (game.getCurrentPlayer().isHuman()) {
//                    game.getCurrentPlayer().play();
//
//                }
//                game.play();

            }
        }.start();
    }

    @Override
    public void start(javafx.stage.Stage primaryStage) throws Exception {
        MenuUi menuUi = new MenuUi();
        menuUi.display(primaryStage);


    }


}
