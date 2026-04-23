import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PlayerSelection playerSelection = new PlayerSelection();
            playerSelection.display();
        });
    }
}
