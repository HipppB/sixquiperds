package com.isep.gone.sixquiperd.core;

import lombok.Getter;

import java.util.*;

@Getter
public class Round {
    private final int turn = 0;
    private final List<Player> players;
    private Deque<Card> deck;
    private Board board;

    public Round(List<Player> players, List<Card> initialDeck) {
        this.players = players;
        Collections.shuffle(initialDeck);
        this.deck = new ArrayDeque<>(initialDeck);
        giveCardsToPlayers();
        this.board = new Board(deck);
    }

    private void giveCardsToPlayers() {
        players.forEach(player -> {
            List<Card> playerCards = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                playerCards.add(deck.remove());
            }
            player.initHand(playerCards);
        });
    }

    public void play() {

    }

    public Card movePlayerCard(Player player) {
        return null;
    }

}
