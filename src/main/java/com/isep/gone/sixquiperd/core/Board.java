package com.isep.gone.sixquiperd.core;

import lombok.Data;
import lombok.NonNull;

import java.util.*;

@Data
public class Board {
    @NonNull List<Deque<Card>> rows = new ArrayList<>();

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

    public boolean addCardToBoard(Card card, Player player) {
        var usableRows = new ArrayList<>(this.rows.stream().filter(row -> {
            assert row.peek() != null;
            return row.peek().cardNumber < card.cardNumber;
        }).toList());
        if (usableRows.isEmpty()) {
            return false;
        }
        usableRows.sort(Comparator.comparingInt(o -> o.peek().cardNumber));
        Deque<Card> row = rows.get(rows.size() - 1);
        int rowNumber = this.rows.indexOf(row);
        if (row.size() == 5) {
            player.addScore(row.stream().mapToInt(card1 -> card1.beefHead).sum());
            row = new ArrayDeque<>();
        }
        row.add(card);
        this.rows.set(rowNumber, row);
        return true;
    }

    public void addCardToBoardRow(Card card, int rowNumber, Player player) {
        System.out.println("Adding card " + card.cardNumber + " to row " + rowNumber + " for player " + player.getName());
        Deque<Card> row = rows.get(rowNumber);
        if (row.size() == 5) {
            player.addScore(row.stream().mapToInt(card1 -> card1.beefHead).sum());
            row = new ArrayDeque<>();
        }
        row.add(card);
        rows.set(rowNumber, row);
    }

    protected void addCardToReturn(Card card) {
        cardsToReturn.add(card);
    }

    protected void removeCardToReturn(Card card) {
        cardsToReturn.remove(card);
    }

    protected boolean needsToChooseARow(Player player) {
        for (var row : rows) {
            if (row.peek().getCardNumber() < player.getCardToPlayNumber()) {
                return false;
            }
        }
        return true;
    }
}
