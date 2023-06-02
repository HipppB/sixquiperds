package com.isep.gone.sixquiperd.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoundTest {

    private List<Player> players;
    private List<Card> initialDeck;

    @BeforeEach
    void setUp() {
        players = new ArrayList<>();
        players.add(new Player("Player 1", true));
        players.add(new Player("Player 2", true));
        initialDeck = new ArrayList<>();
        for (int i = 1; i <= 104; i++) {
            initialDeck.add(new Card(i, i % 6 + 1));
        }
    }

    @Test
    void testConstructor() {
        Round round = new Round(players, initialDeck);
        assertEquals(2, round.getPlayers().size());
        assertEquals(80, round.getDeck().size());
        assertEquals(10, round.getPlayers().get(0).getHand().size());
        assertEquals(10, round.getPlayers().get(1).getHand().size());
        assertEquals(4, round.getBoard().getRows().size());
        assertEquals(1, round.getBoard().getRows().get(0).size());
        assertEquals(1, round.getTurn());
        assertEquals(players.get(0), round.getCurrentPlayer());
        assertEquals(RoundState.CHOOSE_CARD, round.getState());
    }

    @Test
    void testChangeState() {
        Round round = new Round(players, initialDeck);
        assertEquals(RoundState.CHOOSE_CARD, round.getState());

    }

    @Test
    void isFinished() {
        Round round = new Round(players, initialDeck);
        assertEquals(false, round.isFinished());
    }
}