package com.isep.gone.sixquiperd.core;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Player {
    @NonNull String name;
    Integer score = 0;
    List<Card> hand = List.of();

    public Player(@NotNull String name) {
        this.name = name;
    }
    public void initHand(List<Card> cards) throws IllegalAccessException {
        if(cards.size() != 10){
            throw new IllegalAccessException("Card length must be 10");
        }
    }
    public void addScore(Integer score) {
        this.score += score;
    }
}
