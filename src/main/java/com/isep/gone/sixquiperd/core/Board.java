package com.isep.gone.sixquiperd.core;

import lombok.Data;
import lombok.NonNull;

import java.util.*;

@Data
public class Board {
    private @NonNull List<Deque<Card>> rows = new ArrayList<>();

    private List<Card> cardsToReturn = new ArrayList<>();

    public Board(Deque<Card> cards) {
        if (cards.size() < 4) {
            throw new IllegalArgumentException("Cannot play with less than 4 cards !");
        }
        for (int i = 0; i < 4; i++) {
            Deque<Card> deque = new ArrayDeque<>();
            deque.add(cards.remove());
            rows.add(deque);
        }
    }

    protected void addCardToBoard(Card card, Player player) {
        var usableRows = new ArrayList<>(this.rows.stream().filter(row -> {
            assert row.peek() != null;
            return row.peek().getCardNumber() < card.getCardNumber();
        }).toList());
        if (usableRows.isEmpty()) {
            return;
        }
        var comparedRows = usableRows.stream().sorted(Comparator.comparing(row -> {
            assert row.peek() != null;
            return row.peek().getCardNumber();
        })).toList();
        Deque<Card> row = comparedRows.get(comparedRows.size() - 1);
        int rowNumber = this.rows.indexOf(row);
        if (row.size() == 5) {
            player.addScore(row.stream().mapToInt(Card::getBeefHead).sum());
            row = new ArrayDeque<>();
        }
        row.add(card);
        this.rows.set(rowNumber, row);
        this.cardsToReturn.remove(card);
    }

    protected void addCardToBoardRow(Card card, int rowNumber, Player player) {
        Deque<Card> row = rows.get(rowNumber);
        player.addScore(row.stream().mapToInt(Card::getBeefHead).sum());
        row = new ArrayDeque<>();
        row.add(card);
        this.rows.set(rowNumber, row);
        this.cardsToReturn.remove(card);
    }

    protected void addCardToReturn(Card card) {
        cardsToReturn.add(card);
    }


    protected boolean needsToChooseARow(Player player) {
        for (var row : rows) {
            assert row.peek() != null;
            if (row.peek().getCardNumber() < player.getCardToPlayNumber()) {
                return false;
            }
        }
        return true;
    }
}
