package com.isep.gone.sixquiperd.core;

import java.util.*;

public class Round {
    List<Player> players;
    Deque<Card> deck;
    Board board;

    public Round(List<Player>players, List<Card> initialDeck) {
        this.players = players;
        Collections.shuffle(initialDeck);
        this.deck = new ArrayDeque<>(initialDeck);
        giveCardsToPlayers();
        this.board = new Board(deck);
    }

    private void giveCardsToPlayers() {
        players.forEach(player -> {
            List<Card> playerCards = new ArrayList<>();
            for (int i = 0; i<10; i++){
                playerCards.add(deck.remove());
            }
            player.initHand(playerCards);
        });
    }

    public void play() {
        for (int i = 0; i < 10; i++){
            System.out.println(i);
        }
    }
}
