package ru.netology.statistic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private Game game;
    private Player player1;
    private Player player2;
    private Player player3;
    private Player duplicatePlayer;

    @BeforeEach
    public void setUp() {
        game = new Game();
        player1 = new Player(1, "Alice", 10);
        player2 = new Player(2, "Bob", 15);
        player3 = new Player(3, "Charlie", 10);
        duplicatePlayer = new Player(4, "Alice", 20);
    }

    @Test
    public void testRegisterPlayer() {
        game.register(player1);
        assertEquals(player1, game.findByName("Alice"));
    }

    @Test
    public void testFindPlayerWithDuplicateNames() {
        game.register(player1);
        game.register(duplicatePlayer);
        assertThrows(DuplicatePlayerException.class, () -> game.findByName("Alice"));
    }

    @Test
    public void testRoundPlayer1Wins() {
        game.register(player1);
        game.register(player2);
        assertEquals(1, game.round("Bob", "Alice"));
    }

    @Test
    public void testRoundPlayer2Wins() {
        game.register(player1);
        game.register(player2);
        assertEquals(2, game.round("Alice", "Bob"));
    }

    @Test
    public void testRoundDraw() {
        game.register(player1);
        game.register(player3);
        assertEquals(0, game.round("Alice", "Charlie"));
    }

    @Test
    public void testPlayerNotRegistered() {
        game.register(player1);
        assertThrows(NotRegisteredException.class, () -> game.round("Alice", "Bob"));
    }

    @Test
    public void testZeroPlayersFound() {
        assertNull(game.findByName("NonExistent"));
    }
}

