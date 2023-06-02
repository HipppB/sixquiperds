package com.isep.gone.sixquiperd.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void testConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new Card(0, 1));
        assertThrows(IllegalArgumentException.class, () -> new Card(105, 1));
        assertThrows(IllegalArgumentException.class, () -> new Card(1, 0));
        assertThrows(IllegalArgumentException.class, () -> new Card(1, 8));
        Card card = new Card(1, 1);
        assertEquals(1, card.getCardNumber());
        assertEquals(1, card.getBeefHead());
        assertFalse(card.isHidden());
    }

    @Test
    void testHide() {
        Card card = new Card(1, 1);
        assertFalse(card.isHidden());
        card.hide();
        assertTrue(card.isHidden());
    }

    @Test
    void testShow() {
        Card card = new Card(1, 1);
        assertFalse(card.isHidden());
        card.hide();
        assertTrue(card.isHidden());
        card.show();
        assertFalse(card.isHidden());
    }
}