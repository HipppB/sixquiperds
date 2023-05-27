package com.isep.gone.sixquiperd.core;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class Card {
    private final @NonNull Integer beefHead;
    private final @NonNull Integer cardNumber;

    private boolean isHidden;

    public Card(int cardNumber, int beefHead) {
        if (cardNumber < 1 || cardNumber > 104)
            throw new IllegalArgumentException("Value must be between 1 and 104");
        if (beefHead < 1 || beefHead > 7)
            throw new IllegalArgumentException("Beef value must be between 1 and 7");
        this.cardNumber = cardNumber;
        this.beefHead = beefHead;
        this.isHidden = false;
    }

    public void hide() {
        this.isHidden = true;
    }

    public void show() {
        this.isHidden = false;
    }

}
