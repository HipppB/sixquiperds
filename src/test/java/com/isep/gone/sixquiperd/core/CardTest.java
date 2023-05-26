package com.isep.gone.sixquiperd.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class CardTest {
    @Test
    void constructor() {
        Card card = new Card(1, 7);
        assertEquals(1, card.getCardNumber());
        assertThrows(IllegalArgumentException.class, () ->  new Card(0, 1));
        assertThrows(IllegalArgumentException.class, () ->  new Card(105, 1));
        assertThrows(IllegalArgumentException.class, () ->  new Card(1, 8));

    }
    @Test
    void getters() {
        Card card = new Card(1, 1);
        assertEquals(1, card.getCardNumber());
        card = new Card(104, 7);
        assertEquals(7, card.getBeefHead());
    }
    @Test
    void isHidden() {
        Card card = new Card(1, 1);
        card.hide();
        assertTrue(card.isHidden());
    }
}