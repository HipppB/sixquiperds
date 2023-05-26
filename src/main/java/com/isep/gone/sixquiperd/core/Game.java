package com.isep.gone.sixquiperd.core;

import java.util.ArrayList;
import java.util.List;

public class Game {

    public static final List<Player> randomList = List.of(Player.randomHumanPlayer, Player.randomBotPlayer);
    List<Player> players;

    Player currentPlayer;
    List<Card> initialDeck = new ArrayList<>();

    Round currentRound;

    public Game(List<Player> players) {
        this.players = players;
        initDeck();
        initRound();
//        play();
    }

    public void initRound() {
        currentRound = new Round(players, initialDeck);
    }

    private void initDeck() {
        for (int i = 1; i <= 104; i++) {
            int beefHead = 0;
            if (i % 11 == 0) {
                beefHead += 5;
            }
            if (i % 10 == 0) {
                beefHead += 3;
            } else if (i % 5 == 0) {
                beefHead += 2;
            }
            if (beefHead == 0) {
                beefHead = 1;
            }
            initialDeck.add(new Card(i, beefHead));
        }
    }

    public void play() {

    }


    public boolean isPlayerTurn() {
        return currentPlayer.isHuman();
    }

    public void chooseCard(Card card) {
        if (currentPlayer.getHand().contains(card)) {
            currentRound.movePlayerCard(currentPlayer);
        } else {
            throw new IllegalArgumentException("Card not in hand");
        }
    }

    public Player getCurrentPlayer() {
        return players.get(0);
    }

    public Board getBoard() {
        return currentRound.getBoard();
    }
}
