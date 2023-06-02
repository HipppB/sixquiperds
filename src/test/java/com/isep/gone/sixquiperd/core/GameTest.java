package com.isep.gone.sixquiperd.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game("John", 2);
    }

    @Test
    void testIsGameOver() {
        assertFalse(game.isGameOver()); // Vérifie que la partie n'est pas terminée au début du jeu

        // Simuler une fin de partie en fixant le score du joueur principal à 66
        game.getMainPlayer().addScore(66);
        assertTrue(game.isGameOver()); // Vérifie que la partie est terminée lorsque le score atteint 66

        // Simuler une fin de partie en fixant le score d'un autre joueur à 66
        game.getPlayers().get(1).addScore(66);
        assertTrue(game.isGameOver()); // Vérifie que la partie est terminée lorsque le score d'un joueur atteint 66

        // Simuler une partie non terminée lorsque les scores sont inférieurs à 66
        game.getMainPlayer().addScore(50);
        assertFalse(game.isGameOver()); // Vérifie que la partie n'est pas terminée lorsque le score est inférieur à 66
    }

    @Test
    void testGetWinner() {
        assertNull(game.getWinner()); // Vérifie qu'aucun gagnant n'est retourné au début du jeu

        // Simuler une fin de partie avec un gagnant
        game.getMainPlayer().addScore(70);
        assertEquals(game.getMainPlayer(), game.getWinner()); // Vérifie que le joueur principal est le gagnant

        // Simuler une partie non terminée
        game.getMainPlayer().addScore(50);
        assertNull(game.getWinner()); // Vérifie qu'aucun gagnant n'est retourné lorsque la partie n'est pas terminée
    }

    @Test
    void testInitDeck() {
        assertEquals(104, game.getInitialDeck().size()); // Vérifie que le deck initial contient 104 cartes

        // Vérifie que les cartes sont correctement initialisées avec les valeurs de beefHead attendues
        for (Card card : game.getInitialDeck()) {
            int cardNumber = card.getCardNumber();
            int expectedBeefHead = 1;
            if (cardNumber % 11 == 0) {
                expectedBeefHead += 5;
            }
            if (cardNumber % 10 == 0) {
                expectedBeefHead += 3;
            } else if (cardNumber % 5 == 0) {
                expectedBeefHead += 2;
            }
            assertEquals(expectedBeefHead, card.getBeefHead());
        }
    }

}
