package com.isep.gone.sixquiperd.core;

import lombok.Getter;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Getter
public class Player {
    public static final Player randomHumanPlayer = new Player("Random Playerix", true);
    public static final Player randomBotPlayer = new Player("Random Bot", false);
    @NonNull
    private final String name;
    private final List<Card> hand = List.of();

    private final boolean isHuman;
    private Integer score = 0;

    public Player(@NotNull String name, boolean isHuman) {
        this.name = name;
        this.isHuman = isHuman;
    }

    public void initHand(List<Card> cards) {
        if (cards.size() != 10) {
            throw new IllegalArgumentException("Card length must be 10");
        }
    }

    public void addScore(Integer score) {
        this.score += score;
    }

    public Card playCard(Card card) {
        if(!hand.contains(card)) {
            throw new IllegalArgumentException("Card must be in hand");
        }
        hand.remove(card);
        return card;
    }
}
