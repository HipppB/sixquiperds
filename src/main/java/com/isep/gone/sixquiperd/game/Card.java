package com.isep.gone.sixquiperd.game;

import lombok.Getter;

public class Card {
    @Getter
    private int value;
    @Getter
    private int beefValue;
    @Getter
    private boolean isHidden;

    public Card(int value, int beefValue) {
        if(value < 1 || value > 104)
            throw new IllegalArgumentException("Value must be between 1 and 104");
        if(beefValue < 1 || beefValue > 7)
            throw new IllegalArgumentException("Beef value must be between 1 and 7");
        this.value = value;
        this.beefValue = beefValue;
    }

    public void hide() {
        this.isHidden = true;
    }



}
