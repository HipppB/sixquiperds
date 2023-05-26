package com.isep.gone.sixquiperd.core;

import lombok.Data;

import java.util.*;

@Data
public class Board {
    ArrayDeque<Card>[] rows = new ArrayDeque[4];
    public Board(Deque<Card> cards) {
        if(cards.size() < 4){
            throw new IllegalArgumentException("Cannot play with less than 4 cards !");
        }
        for (var row: rows){
            row.add(cards.remove());
        }
    }

    public boolean addCardToBoard(Card card) {
        return false;
    }

    public boolean addCardToBoard(Card card, int rowNumber){
        return false;
    }
}
