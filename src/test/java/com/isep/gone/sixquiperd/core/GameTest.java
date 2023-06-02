package com.isep.gone.sixquiperd.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {
    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game("John", 2);
    }

    @Test
    void testInitDeck() {
        assertEquals(104, game.getInitialDeck().size()); // Vérifie que le deck initial contient 104 cartes

        // Vérifie que les cartes sont correctement initialisées avec les valeurs de beefHead attendues
        for (Card card : game.getInitialDeck()) {
            int cardNumber = card.getCardNumber();
            int expectedBeefHead = 0;
            if (cardNumber % 11 == 0) {
                expectedBeefHead += 5;
            }
            if (cardNumber % 10 == 0) {
                expectedBeefHead += 3;
            } else if (cardNumber % 5 == 0) {
                expectedBeefHead += 2;
            }
            if (expectedBeefHead == 0)
                expectedBeefHead = 1;
            assertEquals(expectedBeefHead, card.getBeefHead());
        }
    }

}
