package com.isep.gone.sixquiperd.core;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Game {
    private final List<Player> players;

    private final Player mainPlayer;
    private final List<Card> initialDeck = new ArrayList<>();

    private Round currentRound;

    public Game(String mainPlayerName, Integer nbBots) {
        mainPlayer = new Player(mainPlayerName, true);
        players = new ArrayList<>();
        players.add(mainPlayer);
        for (int i = 0; i < nbBots; i++) {
            players.add(new Player("Bot " + i, false));
        }
        initDeck();
        initRound();
    }

    private void initRound() {
        currentRound = new Round(players, initialDeck);
    }

    public boolean isGameOver() {
        return players.stream().anyMatch(player -> player.getScore() >= 66) && this.currentRound.isFinished();
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
        if (!isGameOver()) {
            if (currentRound.isFinished()) {
                currentRound = new Round(players, initialDeck);
            }
            currentRound.play();
        }
    }

    public void chooseCard(Card card) {
        if (currentRound.getCurrentPlayer().getHand().contains(card) &&
                currentRound.getCurrentPlayer() == mainPlayer &&
                currentRound.getState() == RoundState.WAITING_FOR_CARD) {
            currentRound.getCurrentPlayer().playCard(card);
        } else {
            throw new IllegalArgumentException("Cannot choose card now");
        }
    }

    public void chooseRow(int rowNumber) {
        if (rowNumber >= 0 && rowNumber <= 3 &&
                currentRound.getCurrentPlayer() == mainPlayer &&
                currentRound.getState() == RoundState.WAITING_FOR_ROW) {
            currentRound.getCurrentPlayer().playRow(rowNumber);
        } else {
            throw new IllegalArgumentException("You can't choose row now");
        }
    }

    public Player getCurrentPlayer() {
        return currentRound.getCurrentPlayer();
    }

    public Board getBoard() {
        return currentRound.getBoard();
    }

    public RoundState getRoundState() {
        return currentRound.getState();
    }

    public boolean needsChooseRow() {
        return currentRound.needsChooseARow();
    }
}
