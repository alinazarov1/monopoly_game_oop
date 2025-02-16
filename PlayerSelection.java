import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PlayerSelection {

    private int numberOfPlayers; // Stores the selected number of players

    public void display() {
        // Create the frame for the player selection window
        JFrame frame = new JFrame("Monopoly - Player Selection");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());

        // Label for instructions
        JLabel instructionLabel = new JLabel("Select the number of players:", SwingConstants.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        // Panel to hold the player selection buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout()); // Arrange buttons in a row

        // Create and add buttons for selecting the number of players
        Integer[] playerOptions = { 2, 3, 4, 5, 6, 7, 8 }; // Allow 2 to 8 players
        for (int i = 0; i < playerOptions.length; i++) {
            JButton button = new JButton(String.valueOf(playerOptions[i]));
            int numberOfPlayers = playerOptions[i]; // Capture the value of the button
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    PlayerSelection.this.numberOfPlayers = numberOfPlayers; // Set the selected number of players
                    frame.dispose(); // Close the player selection window
                    startGame(numberOfPlayers); // Start the main game
                }
            });
            buttonPanel.add(button);
        }

        // Start Game button (optional, if you want to keep it)
        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.PLAIN, 14));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the player selection window
                startGame(numberOfPlayers); // Start the main game
            }
        });

        // Add components to the frame
        frame.add(instructionLabel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(startButton, BorderLayout.SOUTH);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Display the frame
        frame.setVisible(true);
    }

    // Modify the startGame method to validate the number of players
    private void startGame(int numberOfPlayers) {
        if (numberOfPlayers < 2 || numberOfPlayers > 8) {
            JOptionPane.showMessageDialog(null, "Please select between 2 and 8 players.");
            display();
            return;
        }
        SwingUtilities.invokeLater(() -> {
            MonopolyBoardSwing.createMonopolyBoard(numberOfPlayers);
        });
    }

}
