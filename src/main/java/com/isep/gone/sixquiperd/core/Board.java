package com.isep.gone.sixquiperd.core;

import lombok.Data;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

@Data
public class Board {
    Deque<Card> row1 = new ArrayDeque<>();
    Deque<Card> row2 = new ArrayDeque<>();
    Deque<Card> row3 = new ArrayDeque<>();
    Deque<Card> row4 = new ArrayDeque<>();

    public Board(List<Card> firstCards) {
        if(firstCards.size() != 4) {
            throw new IllegalArgumentException("The length of the list must be 4");
        }
    }
}
