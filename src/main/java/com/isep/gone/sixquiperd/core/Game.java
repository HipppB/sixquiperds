package com.isep.gone.sixquiperd.core;

import java.util.ArrayList;
import java.util.List;

public class Game {
    List<Player> players;
    List<Card> initialDeck = new ArrayList<>();

    Round currentRound;

    public Game() {
        initDeck();
//        play();
    }

    private void initDeck() {
        for (int i = 1; i <= 104; i++){
            int beefHead = 0;
            if(i%11 == 0){
                beefHead += 5;
            }
            if(i%10 == 0){
                beefHead += 3;
            } else if(i%5 == 0) {
                beefHead += 2;
            }
            if(beefHead == 0) {
                beefHead = 1;
            }
            initialDeck.add(new Card(i, beefHead));
        }
    }

    private void play() {
        while (players.stream().noneMatch(player -> player.getScore() >= 66)) {
            System.out.println("New round");
        }
    }
}
