package com.isep.gone.sixquiperd.server;

import java.util.concurrent.ThreadLocalRandom;

// This class will be the AI of the game, it will be able to play the game
// It returns a random move
public interface DumbAi {
    static int play(int handSize) {
        ThreadLocalRandom tlr = ThreadLocalRandom.current();
        return tlr.nextInt(0, handSize);
    }

    static int chooseRow() {
        ThreadLocalRandom tlr = ThreadLocalRandom.current();
        return tlr.nextInt(0, 4);
    }
}
