package com.isep.gone.sixquiperd.server;

// This class will be the AI of the game, it will be able to play the game
// It returns a random move
public class DumbAi {
    public static int play() {
        return (int) (Math.random() * 10 - 1);
    }

    public static int chooseRow() {
        return (int) (Math.random() * 4 - 1);
    }
}
