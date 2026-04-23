import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class GameRulesTest {
    @Test
    void bankruptPlayerIsDetected() {
        Player player = new Player("P1");
        player.setWallet(-1);

        assertTrue(GameRules.isBankrupt(player));
    }

    @Test
    void winnerDetectedWhenOneActivePlayerRemains() {
        Player p1 = new Player("P1");
        Player p2 = new Player("P2");
        Player p3 = new Player("P3");

        p2.setActive(false);
        p3.setActive(false);

        Player winner = GameRules.findWinner(new Player[] { p1, p2, p3 });

        assertNotNull(winner);
        assertEquals("P1", winner.getName());
    }

    @Test
    void noWinnerWhenMultipleActivePlayersRemain() {
        Player p1 = new Player("P1");
        Player p2 = new Player("P2");

        Player winner = GameRules.findWinner(new Player[] { p1, p2 });

        assertNull(winner);
    }

    @Test
    void nextActivePlayerSkipsInactivePlayers() {
        Player p1 = new Player("P1");
        Player p2 = new Player("P2");
        Player p3 = new Player("P3");

        p2.setActive(false);

        int next = GameRules.getNextActivePlayerIndex(new Player[] { p1, p2, p3 }, 0);

        assertEquals(2, next);
    }
}
