package com.isep.gone.sixquiperd.ui;

import com.isep.gone.sixquiperd.core.Game;

public class BoardController {

    private Game game;

    public BoardController(Game game) {
        this.game = game;
    }

    public void onRowClicked(Integer rowNumber) {
        System.out.println("Row clicked " + rowNumber);
        // TODO
        try {
            this.game.chooseRow(rowNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void onCardHandClicked(Integer cardNumber) {
        System.out.println("Card clicked");
        if (cardNumber >= game.getMainPlayer().getHand().size())
            return;
        try {

            game.chooseCard(game.getMainPlayer().getHand().get(cardNumber));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        game.chooseCard();
        // TODO
    }

    public void onPlayButtonClicked() {

        System.out.println("Play button clicked");
        // TODO
    }
}
