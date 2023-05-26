package com.isep.gone.sixquiperd.ui;

// This class is the representation of a card in the UI, it automatically loads the image of the card
public class Card extends javafx.scene.image.ImageView {
    private final int value;
    private final int beefHead;

    public Card(int value, int beefHead) {
        this.value = value;
        this.beefHead = beefHead;
        this.setImage(new javafx.scene.image.Image("cards/" + value + ".png"));
    }

    public int getValue() {
        return value;
    }

    public int getBeefHead() {
        return beefHead;
    }
}
