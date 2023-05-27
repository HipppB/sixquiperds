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

    private void changeState() {
        if (this.players.stream().allMatch(player -> player.getCardToPlay() != null)) {
            this.state = RoundState.CHOOSE_ROW;
            var clonedPlayers = new ArrayList<>(players);
            clonedPlayers.sort(Comparator.comparingInt(Player::getCardToPlayNumber));
            playerIterator = clonedPlayers.iterator();
            goToNextPlayer();
        } else if (this.players.stream().allMatch(player -> player.getCardToPlay() == null)) {
            this.state = RoundState.CHOOSE_CARD;
            this.turn++;
            this.playerIterator = players.iterator();
            goToNextPlayer();
        } else {
            System.out.println("Error");
            players.forEach(player -> {
                if (player.getCardToPlay() == null) {
                    System.out.println(player.getName() + " has no card to play");
                }
            });
        }
    }

    public void chooseCard() {
        if (this.currentPlayer.isHuman() && this.currentPlayer.getCardToPlay() == null) {
            this.state = RoundState.WAITING_FOR_CARD;
            return;
        }
        if (this.currentPlayer.getCardToPlay() == null) {
            this.currentPlayer.playCard(this.currentPlayer.getHand().get(DumbAi.play(this.currentPlayer.getHand().size())));
            goToNextPlayer();
            return;
        }
        goToNextPlayer();
    }

    public void goToNextPlayer() {
        if (playerIterator.hasNext()) {
            currentPlayer = playerIterator.next();
        } else {
            changeState();
        }
    }

    public void play() {
        switch (state) {
            case CHOOSE_CARD:
                chooseCard();
                break;
            case CHOOSE_ROW:
                chooseRow();
                break;
            case WAITING_FOR_CARD:
                hasPlayerChosenCard();
                break;
            case WAITING_FOR_ROW:
                hasPlayerChosenRow();
                break;
        }
    }

    private void hasPlayerChosenRow() {
        if (this.currentPlayer.getRowToPlay() != -1) {
            this.board.addCardToBoardRow(currentPlayer.getCardToPlay(), currentPlayer.getRowToPlay(), currentPlayer);
            this.currentPlayer.useCard();
            this.currentPlayer.useRow();
            this.state = RoundState.CHOOSE_ROW;
            goToNextPlayer();
        }
    }

    private void hasPlayerChosenCard() {
        if (this.currentPlayer.hasPlayerChosen()) {
            this.state = RoundState.CHOOSE_CARD;
            goToNextPlayer();
        }
    }


    private void chooseRow() {
        if (this.currentPlayer.isHuman() && this.currentPlayer.getRowToPlay() == -1) {
            if (this.board.needsToChooseARow(currentPlayer)) {
                this.state = RoundState.WAITING_FOR_ROW;
            } else {
                this.board.addCardToBoard(currentPlayer.getCardToPlay(), currentPlayer);
                this.currentPlayer.useCard();
                goToNextPlayer();
            }
            return;
        }
        if (this.currentPlayer.getRowToPlay() == -1) {
            if (this.board.needsToChooseARow(currentPlayer)) {
                this.currentPlayer.playRow(DumbAi.chooseRow());
                this.board.addCardToBoardRow(currentPlayer.getCardToPlay(), currentPlayer.getRowToPlay(), currentPlayer);
                this.currentPlayer.useRow();
                this.currentPlayer.useCard();
            } else {
                this.board.addCardToBoard(currentPlayer.getCardToPlay(), currentPlayer);
                this.currentPlayer.useCard();
            }
            goToNextPlayer();
            return;
        }
        this.board.addCardToBoardRow(currentPlayer.getCardToPlay(), currentPlayer.getRowToPlay(), currentPlayer);
        this.currentPlayer.useCard();
        this.currentPlayer.useRow();
        goToNextPlayer();
    }

    public boolean isFinished() {
        return turn > 10;
    }

    public boolean needsChooseARow() {
        return board.needsToChooseARow(currentPlayer);
    }
}
