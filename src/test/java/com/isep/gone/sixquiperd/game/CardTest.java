package com.isep.gone.sixquiperd.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class CardTest {
    @Test
    void constructor() {
        Card card = new Card(1, 7);
        assertEquals(1, card.getValue());
        assertThrows(IllegalArgumentException.class, () ->  new Card(0, 1));
        assertThrows(IllegalArgumentException.class, () ->  new Card(105, 1));
        assertThrows(IllegalArgumentException.class, () ->  new Card(1, 8));

    }
    @Test
    void getters() {
        Card card = new Card(1, 1);
        assertEquals(1, card.getValue());
        card = new Card(104, 7);
        assertEquals(7, card.getBeefValue());
    }
    @Test
    void isHidden() {
        Card card = new Card(1, 1);
        card.hide();
        assertTrue(card.isHidden());
    }
}