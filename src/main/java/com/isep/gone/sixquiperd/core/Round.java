package com.isep.gone.sixquiperd.core;

import com.isep.gone.sixquiperd.server.DumbAi;
import lombok.Getter;

import java.util.*;

@Getter
public class Round {
    private final List<Player> players;
    private int turn = 1;
    private Deque<Card> deck;
    private Board board;
    private Iterator<Player> playerIterator;
    private Player currentPlayer;
    private RoundState state = RoundState.CHOOSE_CARD;

    public Round(List<Player> players, List<Card> initialDeck) {
        this.players = players;
        Collections.shuffle(initialDeck);
        this.deck = new ArrayDeque<>(initialDeck);
        giveCardsToPlayers();
        this.board = new Board(deck);
        this.playerIterator = players.iterator();
        this.currentPlayer = playerIterator.next();
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

    public void chooseCards() {
        if (!currentPlayer.isHuman()) {
            Card cardToPlay = currentPlayer.getHand().get(DumbAi.play(currentPlayer.getHand().size()));
            currentPlayer.playCard(cardToPlay);
            board.addCardToReturn(cardToPlay);
            if (playerIterator.hasNext()) {
                this.currentPlayer = playerIterator.next();
            } else {
                this.state = RoundState.CHOOSE_ROW;
                var clonedPlayers = new ArrayList<>(players);
                clonedPlayers.sort(Comparator.comparingInt(Player::getCardToPlayNumber));
                playerIterator = clonedPlayers.iterator();
                currentPlayer = playerIterator.next();
            }
            return;
        }
        if (currentPlayer.hasPlayerChosen()) {
            this.board.addCardToReturn(currentPlayer.getCardToPlay());
            if (playerIterator.hasNext()) {
                this.currentPlayer = playerIterator.next();
            } else {
                this.state = RoundState.CHOOSE_ROW;
                var clonedPlayers = new ArrayList<>(players);
                clonedPlayers.sort(Comparator.comparingInt(Player::getCardToPlayNumber));
                playerIterator = clonedPlayers.iterator();
                currentPlayer = playerIterator.next();
            }
        }
    }

    public void play() {
        if (turn <= 10) {
            switch (this.state) {
                case CHOOSE_ROW -> chooseRow();
                case CHOOSE_CARD -> chooseCards();
            }
        }
    }

    private void chooseRow() {
        var cardAdded = board.addCardToBoard(currentPlayer.getCardToPlay(), currentPlayer);
        if (cardAdded) {
            this.board.removeCardToReturn(currentPlayer.getCardToPlay());
            currentPlayer.useCard();
        }
        if (!currentPlayer.isHuman()) {
            currentPlayer.playRow(DumbAi.chooseRow());
            board.addCardToBoardRow(currentPlayer.getCardToPlay(), currentPlayer.getRowToPlay(), currentPlayer);
            currentPlayer.useRow();
            board.removeCardToReturn(currentPlayer.getCardToPlay());
            currentPlayer.useCard();
        } else if (!currentPlayer.hasPlayerChosen()) {
            return;
        } else {
            board.addCardToBoardRow(currentPlayer.getCardToPlay(), currentPlayer.getRowToPlay(), currentPlayer);
            currentPlayer.useRow();
            board.removeCardToReturn(currentPlayer.getCardToPlay());
            currentPlayer.useCard();
        }

        if (playerIterator.hasNext()) {
            this.currentPlayer = playerIterator.next();
        } else {
            this.state = RoundState.CHOOSE_CARD;
            turn++;
            playerIterator = players.iterator();
            currentPlayer = playerIterator.next();
        }
    }

    public Card movePlayerCard(Player player) {
        return null;
    }

    public boolean isFinished() {
        return turn > 10;
    }

}
