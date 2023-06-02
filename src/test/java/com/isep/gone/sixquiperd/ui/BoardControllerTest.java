package com.isep.gone.sixquiperd.ui;

import com.isep.gone.sixquiperd.core.Game;
import com.isep.gone.sixquiperd.core.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BoardControllerTest {

    private BoardController boardController;

    @BeforeEach
    void setUp() {
        Game game = new Game(
                "Player 1", 4
        );
        boardController = new BoardController(game);
    }

    @Test
    void getPlayers() {
        List<Player> players = boardController.getPlayers();
        assertEquals(5, players.size());
        assertEquals("Player 1", players.get(0).getName());
    }

    @Test
    void onRowClicked() {
        assertThrows(Exception.class, () -> boardController.onRowClicked(10));
    }

    @Test
    void getCurrentPlayer() {
        Player currentPlayer = boardController.getCurrentPlayer();
        assertEquals("Player 1", currentPlayer.getName());
    }
    
}