package com.isep.gone.sixquiperd.core;

import java.util.List;

public class Game {
    List<Player> players;
    List<Card> deck;
    List<Card> initialDeck;

    Round currentRound;

    public Game() {
        initDeck();
        play();
    }

    private void initDeck() {

    }

    private void play() {
        while (players.stream().noneMatch(player -> player.getScore() >= 66)) {
            System.out.println("New round");
        }
    }
}
