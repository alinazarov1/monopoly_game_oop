import javax.swing.*;

public class main {
    public static void main(String[] args) {
        // Ensure that the GUI updates are done on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            // Create and display the player selection screen
            PlayerSelection playerSelection = new PlayerSelection();
            playerSelection.display();
        });
    }
}
