package com.isep.gone.sixquiperd.server;

// This class will be the AI of the game, it will be able to play the game
// It returns a random move
public class DumbAi {
    public int play() {
        return (int) (Math.random() * 10);
    }
}
