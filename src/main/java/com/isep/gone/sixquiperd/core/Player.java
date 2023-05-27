package com.isep.gone.sixquiperd.core;

import lombok.Getter;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Player {
    public static final Player randomHumanPlayer = new Player("Random Playerix", true);
    public static final Player randomBotPlayer = new Player("Random Bot", false);
    @NonNull
    private final String name;
    private final boolean isHuman;
    private List<Card> hand = new ArrayList<>();
    private Integer score = 0;

    private Card cardToPlay;

    private int rowToPlay;

    public Player(@NotNull String name, boolean isHuman) {
        this.name = name;
        this.isHuman = isHuman;
    }

    protected void initHand(List<Card> cards) {
        if (cards.size() != 10) {
            throw new IllegalArgumentException("Card length must be 10");
        }
        hand.addAll(cards);
    }

    protected void addScore(Integer score) {
        this.score += score;
    }

    public void playCard(Card card) {
        if (!hand.contains(card)) {
            throw new IllegalArgumentException("Card must be in hand");
        }
        hand.remove(card);
        this.cardToPlay = card;
    }

    protected Card useCard() {
        var card = cardToPlay;
        cardToPlay = null;
        return card;
    }

    public void playRow(int rowNumber) {
        if (rowNumber < 0 || rowNumber > 3) {
            throw new IllegalArgumentException("Row number must be between 0 and 3");
        }
        this.rowToPlay = rowNumber;
    }

    protected void useRow() {
        this.rowToPlay = -1;
    }

    protected boolean hasPlayerChosen() {
        return this.cardToPlay != null && this.rowToPlay != -1;
    }

    protected int getCardToPlayNumber() {
        return cardToPlay.getCardNumber();
    }
}
