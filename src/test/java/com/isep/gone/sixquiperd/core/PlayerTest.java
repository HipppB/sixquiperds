package com.isep.gone.sixquiperd.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = Game.TEST_GAME.getMainPlayer();
    }

    @Test
    void testInitHand() {

        assertEquals(10, player.getHand().size()); // Vérifie que la main du joueur contient 10 cartes

        // Vérifie que les cartes dans la main du joueur correspondent aux cartes initiales
        for (int i = 0; i < 10; i++) {
            Card card = player.getHand().get(i);
            assertTrue(player.getHand().contains(card));
        }
    }

    @Test
    void testAddScore() {
        player.addScore(10);
        assertEquals(10, player.getScore()); // Vérifie que le score du joueur est correct après avoir ajouté 10 points

        player.addScore(5);
        assertEquals(15, player.getScore()); // Vérifie que le score du joueur est correct après avoir ajouté 5 points supplémentaires
    }

    @Test
    void testPlayCard() {
        Card cardToPlay = player.getHand().get(0);
        player.playCard(cardToPlay);
        assertFalse(player.getHand().contains(cardToPlay)); // Vérifie que la carte a été retirée de la main du joueur
        assertEquals(cardToPlay, player.getCardToPlay()); // Vérifie que la carte à jouer correspond à la carte choisie
    }
    
    @Test
    void testPlayRow() {
        player.playRow(2);
        assertEquals(2, player.getRowToPlay()); // Vérifie que le numéro de la rangée à jouer est correct

        assertThrows(IllegalArgumentException.class, () -> player.playRow(-1)); // Vérifie que le joueur ne peut pas choisir un numéro de rangée négatif
        assertThrows(IllegalArgumentException.class, () -> player.playRow(4)); // Vérifie que le joueur ne peut pas choisir un numéro de rangée supérieur à 3
    }

    @Test
    void testUseRow() {
        player.useRow();
        assertEquals(-1, player.getRowToPlay()); // Vérifie que le numéro de la rangée à jouer est réinitialisé à -1 après l'utilisation de la rangée
    }


}
