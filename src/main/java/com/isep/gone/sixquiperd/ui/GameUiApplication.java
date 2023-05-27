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
            BoardUi boardUi = new BoardUi(primaryStage);

            Player player = game.getMainPlayer();


            @Override
            public void handle(long now) {
                boardUi.display(game.getBoard(), player);

                System.out.println("GameUiApplication: handle");
//                boardUi.display(game.getBoard(), player);
//
//                if (game.getCurrentPlayer() == player) {
//
//                } else {
                try {
                    Thread.sleep(1000);
                    System.out.println("GameUiApplication: handle: sleep");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                    game.play();
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
