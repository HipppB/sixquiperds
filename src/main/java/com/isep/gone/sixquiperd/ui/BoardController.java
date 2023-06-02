package com.isep.gone.sixquiperd.ui;

import com.isep.gone.sixquiperd.core.Game;
import com.isep.gone.sixquiperd.core.Player;

import java.util.List;

public class BoardController {

    private Game game;

    public BoardController(Game game) {
        this.game = game;
    }

    public List<Player> getPlayers() {

        return game.getPlayers();
    }


    public void onRowClicked(Integer rowNumber) {
        try {
            this.game.chooseRow(rowNumber);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Player getCurrentPlayer() {
        return game.getCurrentPlayer();
    }

    public void onCardHandClicked(Integer cardNumber) {
        if (cardNumber >= game.getMainPlayer().getHand().size())
            return;
        try {
            game.chooseCard(game.getMainPlayer().getHand().get(cardNumber));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
