import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// Define the Tokens enum
enum Tokens {
    A, B, C, D, E, G, H, I
}
// Removed unused walletLabels variable

public class MonopolyBoardSwing extends JFrame {
    // Arrays to store property names
    private static Player[] players;
    private static int currentPlayerIndex = 0;
    private static JLabel[] playerTokens;
    private static final int BOARD_SIZE = 40;
    private static JPanel playerPanel;
    private static JLabel[] walletLabels;
    private static JPanel[] playerPanels;
    private static final Chance chance = new Chance(); // Add this as a class variable

    // Add these coordinate arrays to store token positions
    private static final int[] xCoordinates = {
            748, 678, 608, 538, 468, 398, 328, 258, 188, 118, 52, // Bottom row (0-10)
            52, 52, 52, 52, 52, 52, 52, 52, 52, 52, // Left column (11-20)
            52, 118, 188, 258, 328, 398, 468, 538, 608, 678, // Top row (21-30)
            748, 748, 748, 748, 748, 748, 748, 748, 748, 748 // Right column (31-40)
    };

    private static final int[] yCoordinates = {
            724, 724, 724, 724, 724, 724, 724, 724, 724, 724, 724, // Bottom row (0-10)
            654, 584, 514, 444, 374, 304, 234, 164, 94, 28, // Left column (11-20)
            28, 28, 28, 28, 28, 28, 28, 28, 28, 28, // Top row (21-30)
            94, 164, 234, 304, 374, 444, 514, 584, 654, 724 // Right column (31-40)
    };

    public static void createMonopolyBoard(int numberOfPlayers) {
        JFrame frame = new JFrame("Monopoly Board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400, 900);
        frame.setResizable(false);

        BackgroundPanel boardPanel = new BackgroundPanel("monopoly.png");
        boardPanel.setLayout(null);

        frame.setFocusable(true);
        boardPanel.setLayout(null);

        // Create spaces (Space[])
        Space[] spaces = new Space[40]; // 40 spaces on the board

        // Populate the spaces array with all spaces
        spaces[0] = new Space("GO", "Corner", "Collect $200 as you pass GO.");
        spaces[1] = new Space("MEDITERRANEAN AVENUE", "Property", "Price: $60, Rent: $15");
        spaces[2] = new Space("COMMUNITY CHEST", "Chance", "Draw a card from the deck.");
        spaces[3] = new Space("BALTIC AVENUE", "Property", "Price: $60, Rent: $20");
        spaces[4] = new Space("INCOME TAX", "Tax", "Pay $200 tax.");
        spaces[5] = new Space("READING RAILROAD", "Railroad", "Price: $200, Rent: $50");
        spaces[6] = new Space("ORIENTAL AVENUE", "Property", "Price: $100, Rent: $25");
        spaces[7] = new Space("CHANCE", "Chance", "Draw a card from the deck.");
        spaces[8] = new Space("VERMONT AVENUE", "Property", "Price: $100, Rent: $25");
        spaces[9] = new Space("CONNECTICUT AVENUE", "Property", "Price: $120, Rent: $30");
        spaces[10] = new Space("JAIL", "Corner", "Just visiting or go to Jail.");
        spaces[11] = new Space("ST. CHARLES PLACE", "Property", "Price: $140, Rent: $35");
        spaces[12] = new Space("ELECTRIC COMPANY", "Utility", "Price: $150, Rent: 4x roll of dice");
        spaces[13] = new Space("STATES AVENUE", "Property", "Price: $140, Rent: $35");
        spaces[14] = new Space("VIRGINIA AVENUE", "Property", "Price: $160, Rent: $40");
        spaces[15] = new Space("PENNSYLVANIA RAILROAD", "Railroad", "Price: $200, Rent: $50");
        spaces[16] = new Space("ST. JAMES PLACE", "Property", "Price: $180, Rent: $45");
        spaces[17] = new Space("COMMUNITY CHEST", "Chance", "Draw a card from the deck.");
        spaces[18] = new Space("TENNESSEE AVENUE", "Property", "Price: $180, Rent: $45");
        spaces[19] = new Space("NEW YORK AVENUE", "Property", "Price: $200, Rent: $50");
        spaces[20] = new Space("FREE PARKING", "Corner", "Free parking.");
        spaces[21] = new Space("KENTUCKY AVENUE", "Property", "Price: $220, Rent: $55");
        spaces[22] = new Space("CHANCE", "Chance", "Draw a card from the deck.");
        spaces[23] = new Space("INDIANA AVENUE", "Property", "Price: $220, Rent: $55");
        spaces[24] = new Space("ILLINOIS AVENUE", "Property", "Price: $240, Rent: $60");
        spaces[25] = new Space("B&O RAILROAD", "Railroad", "Price: $200, Rent: $50");
        spaces[26] = new Space("ATLANTIC AVENUE", "Property", "Price: $260, Rent: $65");
        spaces[27] = new Space("VENTNOR AVENUE", "Property", "Price: $260, Rent: $65");
        spaces[28] = new Space("WATER WORKS", "Utility", "Price: $150, Rent: 4x roll of dice");
        spaces[29] = new Space("MARVIN GARDENS", "Property", "Price: $280, Rent: $70");
        spaces[30] = new Space("GO TO JAIL", "Corner", "Go directly to Jail. Do not pass GO.");
        spaces[31] = new Space("PACIFIC AVENUE", "Property", "Price: $300, Rent: $75");
        spaces[32] = new Space("NORTH CAROLINA AVENUE", "Property", "Price: $300, Rent: $75");
        spaces[33] = new Space("COMMUNITY CHEST", "Chance", "Draw a card from the deck.");
        spaces[34] = new Space("PENNSYLVANIA AVENUE", "Property", "Price: $320, Rent: $80");
        spaces[35] = new Space("SHORT LINE", "Railroad", "Price: $200, Rent: $50");
        spaces[36] = new Space("CHANCE", "Chance", "Draw a card from the deck.");
        spaces[37] = new Space("PARK PLACE", "Property", "Price: $350, Rent: $85");
        spaces[38] = new Space("LUXURY TAX", "Tax", "Pay $100 tax.");
        spaces[39] = new Space("BOARDWALK", "Property", "Price: $400, Rent: $100");

        // Corner panels with hidden names

        JPanel goPanel = createGridSquare(spaces[0], 103, 103);
        goPanel.setBounds(696, 696, 103, 103);
        boardPanel.add(goPanel);

        JPanel jailPanel = createGridSquare(spaces[10], 105, 103);
        jailPanel.setBounds(0, 696, 105, 103);
        boardPanel.add(jailPanel);

        JPanel freeParkingPanel = createGridSquare(spaces[20], 104, 100);
        freeParkingPanel.setBounds(0, 2, 104, 100);
        boardPanel.add(freeParkingPanel);

        JPanel goToJailPanel = createGridSquare(spaces[30], 104, 100);
        goToJailPanel.setBounds(696, 2, 104, 100);
        boardPanel.add(goToJailPanel);

        // Create rows and columns with hidden names
        // Create rows and columns with hidden names
        JPanel topRow = createRow(spaces, 21, 9, 57, 104);
        topRow.setBounds(99, 3, 600, 100);
        boardPanel.add(topRow);

        // Bottom row (positions 1-9, drawn right to left)
        JPanel bottomRow = createRow(spaces, 1, 9, 57, 106);
        bottomRow.setBounds(98, 696, 602, 102);
        boardPanel.add(bottomRow);

        // Left column (positions 11-20, drawn bottom to top)
        JPanel leftColumn = createColumn(spaces, 11, 9, 106, 57);
        leftColumn.setBounds(0, 99, 104, 600);
        boardPanel.add(leftColumn);

        // Right column (positions 31-40, drawn top to bottom)
        JPanel rightColumn = createColumn(spaces, 31, 9, 106, 57);
        rightColumn.setBounds(694, 99, 104, 600);
        boardPanel.add(rightColumn);

        // Center Area (Middle of the board)
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(null);
        centerPanel.setOpaque(false);
        centerPanel.setBounds(120, 120, 600, 600);

        // Dice Panel
        Dice dice = new Dice();

        JPanel dicePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dice.draw(g, 50, 50);
            }
        };
        dicePanel.setBounds(130, 167, 340, 250);
        dicePanel.setOpaque(false);
        centerPanel.add(dicePanel);

        // Roll Dice Button
        JButton rollDiceButton = new JButton("Roll Dice");
        rollDiceButton.setBounds(250, 370, 100, 30);
        rollDiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dice.rollDice();
                dicePanel.repaint();
            }
        });
        centerPanel.add(rollDiceButton);

        boardPanel.add(centerPanel);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                PlayerSelection playerSelection = new PlayerSelection();
                playerSelection.display();
            }
        });

        // Quit Button
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(backButton);

        buttonPanel.add(rollDiceButton);

        buttonPanel.add(quitButton);
        buttonPanel.setBounds(300, 800, 500, 50);

        boardPanel.add(buttonPanel);

        // Add the background panel to the frame
        frame.add(boardPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Player Panel
        playerPanel = new JPanel();
        playerPanel.setLayout(null);
        playerPanel.setBounds(850, 50, 450, 700);
        playerPanel.setBackground(Color.WHITE);

        Color[] tokenColors = { Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE, Color.MAGENTA, Color.CYAN, Color.PINK,
                new Color(12, 34, 54) };
        Tokens[] tokens = { Tokens.A, Tokens.B, Tokens.C, Tokens.D, Tokens.E, Tokens.G, Tokens.H, Tokens.I };
        walletLabels = new JLabel[numberOfPlayers];
        playerPanels = new JPanel[numberOfPlayers];

        for (int i = 0; i < numberOfPlayers; i++) {
            Player player = new Player("Player " + (i + 1)); // Use constructor to set name and token

            String name = player.getName();
            int wallet = player.getWallet();
            Tokens token = tokens[i]; // Get token (A, B, C...)

            // Assign a unique color from the array (loops if more players)
            Color tokenColor = tokenColors[i % tokenColors.length];

            // Create player panel with token circle
            JPanel playerInfoPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    // Draw Token Circle
                    g.setColor(tokenColor);
                    g.fillOval(80, 40, 40, 40); // Adjusted position

                    // Draw Token Letter inside Circle
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Arial", Font.BOLD, 20));
                    g.drawString(String.valueOf(token), 95, 67

                    ); // Centered text
                }
            };

            playerInfoPanel.setLayout(new BoxLayout(playerInfoPanel, BoxLayout.Y_AXIS));
            playerInfoPanel.setBackground(Color.LIGHT_GRAY);
            playerInfoPanel.setBounds(i < (numberOfPlayers + 1) / 2 ? 10 : 220,
                    ((i % ((numberOfPlayers + 1) / 2)) * 150) + 10, 200, 120);

            JLabel playerLabel = new JLabel(name);
            JLabel walletLabel = new JLabel("Wallet: $" + wallet);
            walletLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            walletLabels[i] = walletLabel;
            playerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            playerPanels[i] = playerInfoPanel;

            playerInfoPanel.add(playerLabel);
            playerInfoPanel.add(walletLabel);
            playerPanel.add(playerInfoPanel);

            // Add vertical separator after left column
            if (i == (numberOfPlayers + 1) / 2 - 1) {
                JSeparator separatorr = new JSeparator(SwingConstants.VERTICAL);
                separatorr.setBounds(213, 0, 10, ((numberOfPlayers + 1) / 2) * 150);
                separatorr.setForeground(Color.BLACK);
                playerPanel.add(separatorr);
            }

            // Add horizontal separators
            if (i < numberOfPlayers - 1) {
                JSeparator separator = new JSeparator();
                separator.setBounds(10, (i * 150) + 150, 380, 10);
                playerPanel.add(separator);
            }
        }
        boardPanel.add(playerPanel);

        players = new Player[numberOfPlayers];
        playerTokens = new JLabel[numberOfPlayers];

        for (int i = 0; i < numberOfPlayers; i++) {
            players[i] = new Player("Player " + (i + 1));
            playerTokens[i] = createPlayerToken(tokens[i], tokenColors[i % tokenColors.length]);
            boardPanel.add(playerTokens[i]);
            positionToken(playerTokens[i], 0); // Position at GO

            // Initially all tokens are invisible
            playerTokens[i].setVisible(false);
        }

        // Make only first player's token visible at start
        playerTokens[0].setVisible(true);

        // Modify the rollDiceButton ActionListener:
        rollDiceButton.addActionListener(e -> {
            // If this is the first roll, make all tokens visible
            boolean isFirstRoll = true;
            for (int i = 1; i < playerTokens.length; i++) {
                if (playerTokens[i].isVisible()) {
                    isFirstRoll = false;
                    break;
                }
            }
            if (isFirstRoll) {
                for (JLabel token : playerTokens) {
                    token.setVisible(true);
                }
            }

            dice.rollDice();
            dicePanel.repaint();

            // Get current player
            Player currentPlayer = players[currentPlayerIndex];

            // Calculate new position
            int diceSum = dice.getDice1() + dice.getDice2();
            int newPosition = (currentPlayer.getPosition() + diceSum) % BOARD_SIZE;

            // Move current player's token
            movePlayer(currentPlayer, newPosition);

            // Check if passed GO
            if (newPosition < currentPlayer.getPosition()) {
                currentPlayer.setWallet(currentPlayer.getWallet() + 200);
                JOptionPane.showMessageDialog(frame,
                        currentPlayer.getName() + " passed GO! Collected $200",
                        "Passed GO",
                        JOptionPane.INFORMATION_MESSAGE);
                updatePlayerPanel(currentPlayerIndex);
            }

            // Handle the space the player landed on
            Space currentSpace = spaces[newPosition];
            handlePropertyLanding(currentPlayer, currentSpace, frame);

            // Update player's position
            currentPlayer.setPosition(newPosition);

            // Move to next player
            currentPlayerIndex = (currentPlayerIndex + 1) % numberOfPlayers;

            // Update the active player's panel
            highlightCurrentPlayer(currentPlayerIndex);

            // Announce next player's turn
            JOptionPane.showMessageDialog(frame,
                    "It's " + players[currentPlayerIndex].getName() + "'s turn!",
                    "Next Turn",
                    JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private static JLabel createPlayerToken(Tokens token, Color color) {
        // Create a JLabel to represent the token
        JLabel tokenLabel = new JLabel(token.toString()) {
            @Override
            protected void paintComponent(Graphics g) {
                // Draw a circle with the specified color
                g.setColor(color);
                g.fillOval(0, 0, 40, 40); // 40x40 circle

                // Draw the token text in the center of the circle
                g.setColor(Color.WHITE); // Text color
                FontMetrics fm = g.getFontMetrics();
                String text = token.toString();
                int textWidth = fm.stringWidth(text);
                int textHeight = fm.getAscent();
                int x = (40 - textWidth) / 2; // Center horizontally
                int y = (40 - textHeight) / 2 + fm.getAscent(); // Center vertically
                g.drawString(text, x, y);
            }
        };

        // Set the size of the JLabel to 40x40 pixels
        tokenLabel.setSize(40, 40); // Fixed size for positioning
        tokenLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tokenLabel.setVerticalAlignment(SwingConstants.CENTER);

        // Add a tooltip with the token name
        tokenLabel.setToolTipText(token.toString());

        return tokenLabel;
    }

    private static void positionToken(JLabel token, int position) {
        token.setLocation(xCoordinates[position], yCoordinates[position]);
    }

    private static void movePlayer(Player player, int newPosition) {
        int playerIndex = -1;
        for (int i = 0; i < players.length; i++) {
            if (players[i] == player) {
                playerIndex = i;
                break;
            }
        }

        if (playerIndex != -1) {
            player.setPosition(newPosition); // Update player's position
            positionToken(playerTokens[playerIndex], newPosition); // Move token on the board
        }
    }

    private static void updatePlayerPanel(int playerIndex) {
        if (playerIndex >= 0 && playerIndex < walletLabels.length) {
            JLabel walletLabel = walletLabels[playerIndex];
            walletLabel.setText("Wallet: $" + players[playerIndex].getWallet());
            walletLabel.revalidate();
            walletLabel.repaint();
        }
    }

    private static void highlightCurrentPlayer(int playerIndex) {
        // Remove highlight from all player panels
        for (JPanel panel : playerPanels) {
            panel.setBackground(Color.LIGHT_GRAY);
        }

        // Highlight current player's panel
        if (playerIndex >= 0 && playerIndex < playerPanels.length) {
            playerPanels[playerIndex].setBackground(new Color(200, 230, 200)); // Light green highlight
        }

        playerPanel.revalidate();
        playerPanel.repaint();
    }

    private static JPanel createGridSquare(Space space, int width, int height) {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Only color if it's a property type space AND it's owned
                if (space.getOwner() != null &&
                        (space.getType().equals("Property") ||
                                space.getType().equals("Railroad") ||
                                space.getType().equals("Utility"))) {

                    int playerIndex = getPlayerIndex(space.getOwner());
                    if (playerIndex != -1) {
                        Color tokenColor = getPlayerTokenColor(playerIndex);
                        g.setColor(tokenColor);
                        // Calculate actual component height
                        int actualHeight = getHeight();
                        // Draw a rectangle that covers the top 30% of the actual panel
                        g.fillRect(0, 0, getWidth(), (int) (actualHeight * 0.3));
                    }
                }
            }
        };

        panel.setPreferredSize(new Dimension(width, height));
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        return panel;
    }

    private static Color getPlayerTokenColor(int playerIndex) {
        // Define token colors (same as in the player initialization)
        Color[] tokenColors = { Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE, Color.MAGENTA, Color.CYAN, Color.PINK,
                new Color(12, 34, 54) };

        if (playerIndex >= 0 && playerIndex < tokenColors.length) {
            return tokenColors[playerIndex];
        }
        return Color.LIGHT_GRAY; // Default color if player index is invalid
    }

    private static JPanel createRow(Space[] spaces, int position, int numSquares, int squareWidth, int squareHeight) {
        JPanel rowPanel = new JPanel(new GridLayout(1, numSquares));
        rowPanel.setOpaque(false);

        // Check if this is the bottom row (positions 1-9)
        boolean isBottomRow = (position == 1);

        if (isBottomRow) {
            // For the bottom row, add properties in reverse order (right to left)
            for (int i = position + numSquares - 1; i >= position; i--) {
                Space currentSpace = spaces[i];
                JPanel square = createGridSquare(currentSpace, squareWidth, squareHeight);
                rowPanel.add(square);
            }
        } else {
            // For other rows, add properties in normal order (left to right)
            for (int i = position; i < position + numSquares; i++) {
                Space currentSpace = spaces[i];
                JPanel square = createGridSquare(currentSpace, squareWidth, squareHeight);
                rowPanel.add(square);
            }
        }

        return rowPanel;
    }

    private static JPanel createColumn(Space[] spaces, int position, int numSquares, int squareWidth,
            int squareHeight) {
        JPanel columnPanel = new JPanel(new GridLayout(numSquares, 1));
        columnPanel.setOpaque(false);

        // Check if this is the left column (positions 11-20)
        boolean isLeftColumn = (position == 11);

        if (isLeftColumn) {
            // For the left column, add properties in reverse order (bottom to top)
            for (int i = position + numSquares - 1; i >= position; i--) {
                Space currentSpace = spaces[i];
                JPanel square = createGridSquare(currentSpace, squareWidth, squareHeight);
                columnPanel.add(square);
            }
        } else {
            // For other columns, add properties in normal order (top to bottom)
            for (int i = position; i < position + numSquares; i++) {
                Space currentSpace = spaces[i];
                JPanel square = createGridSquare(currentSpace, squareWidth, squareHeight);
                columnPanel.add(square);
            }
        }

        return columnPanel;
    }

    private static void handlePropertyLanding(Player currentPlayer, Space currentSpace, JFrame frame) {
        String spaceType = currentSpace.getType();

        if (spaceType.equals("Property") || spaceType.equals("Railroad") || spaceType.equals("Utility")) {
            if (currentSpace.getOwner() == null) {
                showBuyPropertyDialog(currentPlayer, currentSpace, frame);
            } else if (currentSpace.getOwner() != currentPlayer) {
                handleRentPayment(currentPlayer, currentSpace, frame);
            }
        } else if (spaceType.equals("Tax")) {
            handleTaxSpace(currentPlayer, currentSpace, frame);
        } else if (spaceType.equals("Chance")) {
            handleChanceCard(currentPlayer, frame); // Handle Chance card
        } else if (spaceType.equals("Community Chest")) {
            handleCommunityChest(currentPlayer, frame);
        }
    }

    private static void handleChanceCard(Player currentPlayer, JFrame frame) {
        // Draw a Chance card
        String drawnCard = chance.drawchCard();

        // Show the drawn card with a custom icon (optional)
        ImageIcon icon = new ImageIcon("chance_icon.png"); // Add an icon image if desired
        JOptionPane.showMessageDialog(frame,
                drawnCard,
                "Chance Card",
                JOptionPane.INFORMATION_MESSAGE,
                icon);

        // Handle the card's effect
        handleChanceEffect(currentPlayer, drawnCard, frame);
    }

    private static void handleChanceEffect(Player currentPlayer, String drawnCard, JFrame frame) {
        switch (drawnCard) {
            case "Advance to Go (Collect $200)":
                movePlayer(currentPlayer, 0); // Move to GO
                currentPlayer.setWallet(currentPlayer.getWallet() + 200);
                JOptionPane.showMessageDialog(frame,
                        "Advanced to GO! Collected $200",
                        "Advance to GO",
                        JOptionPane.INFORMATION_MESSAGE);
                break;

            case "Bank pays you a dividend of $50":
                currentPlayer.setWallet(currentPlayer.getWallet() + 50);
                JOptionPane.showMessageDialog(frame,
                        "You received a dividend of $50",
                        "Bank Dividend",
                        JOptionPane.INFORMATION_MESSAGE);
                break;

            case "Go directly to Jail – Do not pass Go, do not collect $200":
                movePlayer(currentPlayer, 10); // Move to Jail
                JOptionPane.showMessageDialog(frame,
                        "Go to Jail!\nDo not pass GO, do not collect $200",
                        "Go to Jail",
                        JOptionPane.WARNING_MESSAGE);
                break;

            case "Your building loan matures – Collect $150":
                currentPlayer.setWallet(currentPlayer.getWallet() + 150);
                JOptionPane.showMessageDialog(frame,
                        "Your building loan matured! Collected $150",
                        "Loan Matured",
                        JOptionPane.INFORMATION_MESSAGE);
                break;

            case "Get out of Jail Free – This card may be kept until needed or sold":
                currentPlayer.addGetOutOfJailFreeCard();
                JOptionPane.showMessageDialog(frame,
                        "You received a Get Out of Jail Free card!",
                        "Get Out of Jail Free",
                        JOptionPane.INFORMATION_MESSAGE);
                break;

            case "Advance to Illinois Avenue":
                movePlayer(currentPlayer, 24); // Move to Illinois Avenue
                JOptionPane.showMessageDialog(frame,
                        "Advanced to Illinois Avenue!",
                        "Advance to Illinois Avenue",
                        JOptionPane.INFORMATION_MESSAGE);
                break;

            case "Advance to St. Charles Place":
                movePlayer(currentPlayer, 11); // Move to St. Charles Place
                JOptionPane.showMessageDialog(frame,
                        "Advanced to St. Charles Place!",
                        "Advance to St. Charles Place",
                        JOptionPane.INFORMATION_MESSAGE);
                break;

            case "Advance token to nearest Utility. If unowned, you may buy it.":
                moveToNearestUtility(currentPlayer, frame);
                break;

            case "Advance token to nearest Railroad and pay owner twice the normal rent.":
                moveToNearestRailroad(currentPlayer, frame);
                break;

            case "Pay poor tax of $15":
                currentPlayer.setWallet(currentPlayer.getWallet() - 15);
                JOptionPane.showMessageDialog(frame,
                        "You paid a poor tax of $15",
                        "Poor Tax",
                        JOptionPane.INFORMATION_MESSAGE);
                break;

            case "Take a trip to Reading Railroad":
                movePlayer(currentPlayer, 5); // Move to Reading Railroad
                JOptionPane.showMessageDialog(frame,
                        "You took a trip to Reading Railroad!",
                        "Trip to Reading Railroad",
                        JOptionPane.INFORMATION_MESSAGE);
                break;

            case "Advance to Boardwalk":
                movePlayer(currentPlayer, 39); // Move to Boardwalk
                JOptionPane.showMessageDialog(frame,
                        "Advanced to Boardwalk!",
                        "Advance to Boardwalk",
                        JOptionPane.INFORMATION_MESSAGE);
                break;

            case "You have won a crossword competition – Collect $100":
                currentPlayer.setWallet(currentPlayer.getWallet() + 100);
                JOptionPane.showMessageDialog(frame,
                        "You won a crossword competition! Collected $100",
                        "Crossword Competition",
                        JOptionPane.INFORMATION_MESSAGE);
                break;

            default:
                JOptionPane.showMessageDialog(frame,
                        "No effect for this card.",
                        "Chance Card",
                        JOptionPane.INFORMATION_MESSAGE);
                break;
        }

        // Update the player's panel to reflect any changes
        updatePlayerPanel(getCurrentPlayerIndex());
    }

    private static void moveToNearestUtility(Player currentPlayer, JFrame frame) {
        int currentPosition = currentPlayer.getPosition();
        int nearestUtility = -1;

        // Find the nearest Utility (positions 12 and 28)
        if (currentPosition < 12 || currentPosition >= 28) {
            nearestUtility = 12; // Electric Company
        } else {
            nearestUtility = 28; // Water Works
        }

        movePlayer(currentPlayer, nearestUtility);
        JOptionPane.showMessageDialog(frame,
                "Advanced to the nearest Utility!",
                "Advance to Utility",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private static void moveToNearestRailroad(Player currentPlayer, JFrame frame) {
        int currentPosition = currentPlayer.getPosition();
        int nearestRailroad = -1;

        // Find the nearest Railroad (positions 5, 15, 25, 35)
        if (currentPosition < 5 || currentPosition >= 35) {
            nearestRailroad = 5; // Reading Railroad
        } else if (currentPosition < 15) {
            nearestRailroad = 15; // Pennsylvania Railroad
        } else if (currentPosition < 25) {
            nearestRailroad = 25; // B&O Railroad
        } else {
            nearestRailroad = 35; // Short Line
        }

        movePlayer(currentPlayer, nearestRailroad);
        JOptionPane.showMessageDialog(frame,
                "Advanced to the nearest Railroad!",
                "Advance to Railroad",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private static final CommunityChest communityChest = new CommunityChest();

    private static void handleCommunityChest(Player currentPlayer, JFrame frame) {
        String drawnCard = communityChest.drawCard();
        // Removed unused variable cardParts
        // String cardTitle = cardParts[0]; // Removed unused variable

        // Show the drawn card with custom icon
        ImageIcon icon = new ImageIcon("community_chest_icon.png"); // You can add an icon image
        JOptionPane.showMessageDialog(frame,
                drawnCard,
                "Community Chest Card",
                JOptionPane.INFORMATION_MESSAGE,
                icon);

        // Handle the card's effect
        if (drawnCard.contains("Collect $200")) {
            currentPlayer.setWallet(currentPlayer.getWallet() + 200);
        } else if (drawnCard.contains("Collect $50")) {
            currentPlayer.setWallet(currentPlayer.getWallet() + 50);
        } else if (drawnCard.contains("Collect $100")) {
            currentPlayer.setWallet(currentPlayer.getWallet() + 100);
        } else if (drawnCard.contains("Collect $150")) {
            // For "Collect $150 from each player"

            int totalAmount = 0;
            for (Player p : players) {
                if (p != currentPlayer) {
                    p.setWallet(p.getWallet() - 150);
                    totalAmount += 150;
                    updatePlayerPanel(getPlayerIndex(p));
                }
            }
            currentPlayer.setWallet(currentPlayer.getWallet() + totalAmount);
        } else if (drawnCard.contains("Pay $50")) {
            currentPlayer.setWallet(currentPlayer.getWallet() - 50);
        } else if (drawnCard.contains("Pay $100")) {
            currentPlayer.setWallet(currentPlayer.getWallet() - 100);
        } else if (drawnCard.contains("Pay $150")) {
            currentPlayer.setWallet(currentPlayer.getWallet() - 150);
        } else if (drawnCard.contains("Pay $20")) {
            currentPlayer.setWallet(currentPlayer.getWallet() - 20);
        } else if (drawnCard.contains("Go directly to Jail")) {
            movePlayer(currentPlayer, 10); // Position 10 is Jail
            JOptionPane.showMessageDialog(frame,
                    "Go to Jail!\nDo not pass GO, do not collect $200",
                    "Go to Jail",
                    JOptionPane.WARNING_MESSAGE);
        } else if (drawnCard.contains("Advance to GO")) {
            movePlayer(currentPlayer, 0);
            currentPlayer.setWallet(currentPlayer.getWallet() + 200);
            JOptionPane.showMessageDialog(frame,
                    "Advanced to GO! Collected $200",
                    "Advance to GO",
                    JOptionPane.INFORMATION_MESSAGE);
        } else if (drawnCard.contains("Take a loan")) {
            // Handle loan with interest
            currentPlayer.setWallet(currentPlayer.getWallet() + 150);
            JOptionPane.showMessageDialog(frame,
                    "You took a loan of $150.\nYou will need to pay back $165 (including 10% interest)",
                    "Bank Loan",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        // Update the player's panel to reflect any changes
        updatePlayerPanel(getCurrentPlayerIndex());
    }

    private static void showBuyPropertyDialog(Player currentPlayer, Space space, JFrame frame) {
        String message = String.format("%s\nPrice: $%d\nYour wallet: $%d\nWould you like to buy this property?",
                space.getDescription(), space.getPrice(), currentPlayer.getWallet());

        int choice = JOptionPane.showConfirmDialog(frame, message,
                "Buy " + space.getName() + "?",
                JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            if (currentPlayer.getWallet() >= space.getPrice()) {
                // Purchase the property
                currentPlayer.setWallet(currentPlayer.getWallet() - space.getPrice()); // This line should subtract
                space.setOwner(currentPlayer);
                updatePlayerPanel(getCurrentPlayerIndex());

                JOptionPane.showMessageDialog(frame,
                        "Congratulations! You now own " + space.getName(),
                        "Property Purchased",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame,
                        "Sorry, you don't have enough money to buy this property.",
                        "Insufficient Funds",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private static int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    private static void handleRentPayment(Player currentPlayer, Space space, JFrame frame) {
        int rentAmount = space.getRent();
        Player owner = space.getOwner();

        String message = String.format("This property is owned by %s.\nYou must pay $%d in rent.",
                owner.getName(), rentAmount);

        JOptionPane.showMessageDialog(frame, message, "Pay Rent", JOptionPane.INFORMATION_MESSAGE);

        // Transfer rent money
        currentPlayer.setWallet(currentPlayer.getWallet() - rentAmount);
        owner.setWallet(owner.getWallet() + rentAmount);

        // Update both players' panels
        updatePlayerPanel(getCurrentPlayerIndex());
        updatePlayerPanel(getPlayerIndex(owner));
    }

    private static int getPlayerIndex(Player player) {
        for (int i = 0; i < players.length; i++) {
            if (players[i] == player) {
                return i;
            }
        }
        return -1;
    }

    private static void handleTaxSpace(Player currentPlayer, Space space, JFrame frame) {
        int taxAmount = space.getName().equals("LUXURY TAX") ? 100 : 200;

        JOptionPane.showMessageDialog(frame,
                "You must pay $" + taxAmount + " in tax.",
                "Pay Tax",
                JOptionPane.INFORMATION_MESSAGE);

        currentPlayer.setWallet(currentPlayer.getWallet() - taxAmount);
        updatePlayerPanel(getCurrentPlayerIndex());
    }

    static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        BackgroundPanel(String imagePath) {
            try {
                backgroundImage = new ImageIcon(imagePath).getImage();
            } catch (Exception e) {
                System.err.println("Error loading background image: " + e.getMessage());
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, 800, 800, this);
            }
        }

    }

}
