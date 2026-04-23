import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PlayerTest {
    @Test
    void newPlayerHasExpectedDefaults() {
        Player player = new Player("Player 1");

        assertEquals("Player 1", player.getName());
        assertEquals(1500, player.getWallet());
        assertEquals(0, player.getPosition());
        assertTrue(player.isActive());
    }

    @Test
    void playerCanBeMarkedInactive() {
        Player player = new Player("Player 2");

        player.setActive(false);

        assertFalse(player.isActive());
    }
}
