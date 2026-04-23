import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.*;

public class MonopolyBoardSwing extends JFrame {
    private static final int DESIGN_FRAME_WIDTH = 1400;
    private static final int DESIGN_FRAME_HEIGHT = 900;
    private static final int DESIGN_BOARD_PIXELS = 800;
    private static double uiScale = 1.0;

    // Arrays to store property names
    private static Player[] players;
    private static int currentPlayerIndex = 0;
    private static JLabel[] playerTokens;
    private static JPanel playerPanel;
    private static JLabel[] walletLabels;
    private static JPanel[] playerPanels;
    private static Space[] gameSpaces;
    private static final Chance chance = new Chance(); // Add this as a class variable

    private static int s(int value) {
        return Math.max(1, (int) Math.round(value * uiScale));
    }

    private static void setScaledBounds(Component component, int x, int y, int width, int height) {
        component.setBounds(s(x), s(y), s(width), s(height));
    }

    private static void updateScaleForScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        updateScaleForFrameSize(screen.width - 80, screen.height - 120);
    }

    private static void updateScaleForFrameSize(int frameWidth, int frameHeight) {
        double widthScale = frameWidth / (double) DESIGN_FRAME_WIDTH;
        double heightScale = frameHeight / (double) DESIGN_FRAME_HEIGHT;
        uiScale = Math.min(widthScale, heightScale);
        uiScale = Math.min(1.2, Math.max(0.55, uiScale));
    }

    private static void applyScaledLayout(
            JPanel goPanel,
            JPanel jailPanel,
            JPanel freeParkingPanel,
            JPanel goToJailPanel,
            JPanel topRow,
            JPanel bottomRow,
            JPanel leftColumn,
            JPanel rightColumn,
            JPanel centerPanel,
            JPanel dicePanel,
            JButton rollDiceButton,
            JPanel buttonPanel,
            JPanel[] playerInfoPanels,
            JSeparator verticalSeparator,
            JSeparator[] horizontalSeparators,
            int numberOfPlayers) {

        setScaledBounds(goPanel, 696, 696, 103, 103);
        setScaledBounds(jailPanel, 0, 696, 105, 103);
        setScaledBounds(freeParkingPanel, 0, 2, 104, 100);
        setScaledBounds(goToJailPanel, 696, 2, 104, 100);

        setScaledBounds(topRow, 99, 3, 600, 100);
        setScaledBounds(bottomRow, 98, 696, 602, 102);
        setScaledBounds(leftColumn, 0, 99, 104, 600);
        setScaledBounds(rightColumn, 694, 99, 104, 600);

        setScaledBounds(centerPanel, 120, 120, 600, 600);
        setScaledBounds(dicePanel, 130, 167, 340, 250);
        setScaledBounds(rollDiceButton, 250, 370, 100, 30);

        setScaledBounds(buttonPanel, 300, 800, 500, 50);
        setScaledBounds(playerPanel, 850, 50, 450, 700);

        for (int i = 0; i < playerInfoPanels.length; i++) {
            setScaledBounds(playerInfoPanels[i],
                    i < (numberOfPlayers + 1) / 2 ? 10 : 220,
                    ((i % ((numberOfPlayers + 1) / 2)) * 150) + 10,
                    200,
                    120);
        }

        if (verticalSeparator != null) {
            setScaledBounds(verticalSeparator, 213, 0, 10, ((numberOfPlayers + 1) / 2) * 150);
        }

        for (int i = 0; i < horizontalSeparators.length; i++) {
            if (horizontalSeparators[i] != null) {
                setScaledBounds(horizontalSeparators[i], 10, (i * 150) + 150, 380, 10);
            }
        }

        if (playerTokens != null && players != null) {
            int tokenSize = s(40);
            for (int i = 0; i < playerTokens.length; i++) {
                JLabel token = playerTokens[i];
                if (token != null) {
                    token.setSize(tokenSize, tokenSize);
                    token.setFont(new Font("Arial", Font.BOLD, Math.max(12, s(16))));
                    positionToken(token, players[i].getPosition());
                }
            }
        }

        playerPanel.revalidate();
        playerPanel.repaint();
    }

    private static class BoardGridSection {
        JPanel goPanel;
        JPanel jailPanel;
        JPanel freeParkingPanel;
        JPanel goToJailPanel;
        JPanel topRow;
        JPanel bottomRow;
        JPanel leftColumn;
        JPanel rightColumn;
    }

    private static class PlayerPanelSection {
        JPanel[] playerInfoPanels;
        JSeparator[] horizontalSeparators;
        JSeparator verticalSeparator;
    }

    private static BoardGridSection buildBoardGridSection(JPanel boardPanel, Space[] spaces) {
        BoardGridSection section = new BoardGridSection();

        section.goPanel = createGridSquare(spaces[0], s(103), s(103));
        setScaledBounds(section.goPanel, 696, 696, 103, 103);
        boardPanel.add(section.goPanel);

        section.jailPanel = createGridSquare(spaces[10], s(105), s(103));
        setScaledBounds(section.jailPanel, 0, 696, 105, 103);
        boardPanel.add(section.jailPanel);

        section.freeParkingPanel = createGridSquare(spaces[20], s(104), s(100));
        setScaledBounds(section.freeParkingPanel, 0, 2, 104, 100);
        boardPanel.add(section.freeParkingPanel);

        section.goToJailPanel = createGridSquare(spaces[30], s(104), s(100));
        setScaledBounds(section.goToJailPanel, 696, 2, 104, 100);
        boardPanel.add(section.goToJailPanel);

        section.topRow = createRow(spaces, 21, 9, s(57), s(104));
        setScaledBounds(section.topRow, 99, 3, 600, 100);
        boardPanel.add(section.topRow);

        section.bottomRow = createRow(spaces, 1, 9, s(57), s(106));
        setScaledBounds(section.bottomRow, 98, 696, 602, 102);
        boardPanel.add(section.bottomRow);

        section.leftColumn = createColumn(spaces, 11, 9, s(106), s(57));
        setScaledBounds(section.leftColumn, 0, 99, 104, 600);
        boardPanel.add(section.leftColumn);

        section.rightColumn = createColumn(spaces, 31, 9, s(106), s(57));
        setScaledBounds(section.rightColumn, 694, 99, 104, 600);
        boardPanel.add(section.rightColumn);

        return section;
    }

    private static PlayerPanelSection buildPlayerPanelSection(
            JPanel boardPanel,
            int numberOfPlayers,
            Color[] tokenColors,
            Tokens[] tokens) {

        PlayerPanelSection section = new PlayerPanelSection();

        playerPanel = new JPanel();
        playerPanel.setLayout(null);
        setScaledBounds(playerPanel, 850, 50, 450, 700);
        playerPanel.setBackground(Color.WHITE);

        walletLabels = new JLabel[numberOfPlayers];
        playerPanels = new JPanel[numberOfPlayers];
        section.playerInfoPanels = new JPanel[numberOfPlayers];
        section.horizontalSeparators = new JSeparator[Math.max(0, numberOfPlayers - 1)];

        for (int i = 0; i < numberOfPlayers; i++) {
            Player player = new Player("Player " + (i + 1));

            String name = player.getName();
            int wallet = player.getWallet();
            Tokens token = tokens[i];

            Color tokenColor = tokenColors[i % tokenColors.length];

            JPanel playerInfoPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.setColor(tokenColor);
                    g.fillOval(s(80), s(40), s(40), s(40));

                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Arial", Font.BOLD, s(20)));
                    g.drawString(String.valueOf(token), s(95), s(67));
                }
            };

            playerInfoPanel.setLayout(new BoxLayout(playerInfoPanel, BoxLayout.Y_AXIS));
            playerInfoPanel.setBackground(Color.LIGHT_GRAY);
            setScaledBounds(playerInfoPanel,
                    i < (numberOfPlayers + 1) / 2 ? 10 : 220,
                    ((i % ((numberOfPlayers + 1) / 2)) * 150) + 10,
                    200,
                    120);

            JLabel playerLabel = new JLabel(name);
            JLabel walletLabel = new JLabel("Wallet: $" + wallet);
            walletLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            playerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            walletLabels[i] = walletLabel;
            playerPanels[i] = playerInfoPanel;
            section.playerInfoPanels[i] = playerInfoPanel;

            playerInfoPanel.add(playerLabel);
            playerInfoPanel.add(walletLabel);
            playerPanel.add(playerInfoPanel);

            if (i == (numberOfPlayers + 1) / 2 - 1) {
                JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
                setScaledBounds(separator, 213, 0, 10, ((numberOfPlayers + 1) / 2) * 150);
                separator.setForeground(Color.BLACK);
                playerPanel.add(separator);
                section.verticalSeparator = separator;
            }

            if (i < numberOfPlayers - 1) {
                JSeparator separator = new JSeparator();
                setScaledBounds(separator, 10, (i * 150) + 150, 380, 10);
                playerPanel.add(separator);
                section.horizontalSeparators[i] = separator;
            }
        }

        boardPanel.add(playerPanel);
        return section;
    }

    private static void configureTurnController(
            JButton rollDiceButton,
            Dice dice,
            JPanel dicePanel,
            JFrame frame,
            int numberOfPlayers,
            Space[] spaces) {

        rollDiceButton.addActionListener(e -> {
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

            Player currentPlayer = players[currentPlayerIndex];
            int diceSum = dice.getDice1() + dice.getDice2();
            int newPosition = (currentPlayer.getPosition() + diceSum) % BoardData.BOARD_SIZE;

            movePlayer(currentPlayer, newPosition);

            if (newPosition < currentPlayer.getPosition()) {
                currentPlayer.setWallet(currentPlayer.getWallet() + 200);
                JOptionPane.showMessageDialog(frame,
                        currentPlayer.getName() + " passed GO! Collected $200",
                        "Passed GO",
                        JOptionPane.INFORMATION_MESSAGE);
                updatePlayerPanel(currentPlayerIndex);
            }

            Space currentSpace = spaces[newPosition];
            handlePropertyLanding(currentPlayer, currentSpace, frame);
            currentPlayer.setPosition(newPosition);

            if (isPlayerBankrupt(currentPlayer)) {
                eliminatePlayer(currentPlayer, frame);
            }

            Player winner = GameRules.findWinner(players);
            if (winner != null) {
                announceWinnerAndDisableRoll(winner, frame, rollDiceButton);
                highlightCurrentPlayer(getPlayerIndex(winner));
                return;
            }

            currentPlayerIndex = GameRules.getNextActivePlayerIndex(players, currentPlayerIndex);
            if (currentPlayerIndex == -1) {
                rollDiceButton.setEnabled(false);
                return;
            }

            highlightCurrentPlayer(currentPlayerIndex);

            JOptionPane.showMessageDialog(frame,
                    "It's " + players[currentPlayerIndex].getName() + "'s turn!",
                    "Next Turn",
                    JOptionPane.INFORMATION_MESSAGE);
        });
    }

    public static void createMonopolyBoard(int numberOfPlayers) {
        updateScaleForScreen();

        JFrame frame = new JFrame("Monopoly Board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(s(DESIGN_FRAME_WIDTH), s(DESIGN_FRAME_HEIGHT));
        frame.setMinimumSize(new Dimension(900, 620));
        frame.setResizable(true);

        BoardBackgroundPanel boardPanel = new BoardBackgroundPanel("monopoly.png", () -> s(DESIGN_BOARD_PIXELS));
        boardPanel.setLayout(null);

        frame.setFocusable(true);
        boardPanel.setLayout(null);

        Space[] spaces = BoardData.createSpaces();
        gameSpaces = spaces;
        BoardGridSection boardGridSection = buildBoardGridSection(boardPanel, spaces);

        // Center Area (Middle of the board)
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(null);
        centerPanel.setOpaque(false);
        setScaledBounds(centerPanel, 120, 120, 600, 600);

        // Dice Panel
        Dice dice = new Dice();

        JPanel dicePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dice.draw(g, s(50), s(50));
            }
        };
        setScaledBounds(dicePanel, 130, 167, 340, 250);
        dicePanel.setOpaque(false);
        centerPanel.add(dicePanel);

        // Roll Dice Button
        JButton rollDiceButton = new JButton("Roll Dice");
        setScaledBounds(rollDiceButton, 250, 370, 100, 30);

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
        setScaledBounds(buttonPanel, 300, 800, 500, 50);

        boardPanel.add(buttonPanel);

        Color[] tokenColors = BoardData.TOKEN_COLORS;
        Tokens[] tokens = BoardData.TOKENS;
        PlayerPanelSection playerPanelSection = buildPlayerPanelSection(boardPanel, numberOfPlayers, tokenColors, tokens);

        // Add the background panel to the frame
        frame.add(boardPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

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

        configureTurnController(rollDiceButton, dice, dicePanel, frame, numberOfPlayers, spaces);

        applyScaledLayout(
                boardGridSection.goPanel,
                boardGridSection.jailPanel,
                boardGridSection.freeParkingPanel,
                boardGridSection.goToJailPanel,
                boardGridSection.topRow,
                boardGridSection.bottomRow,
                boardGridSection.leftColumn,
                boardGridSection.rightColumn,
                centerPanel,
                dicePanel,
                rollDiceButton,
                buttonPanel,
                playerPanelSection.playerInfoPanels,
                playerPanelSection.verticalSeparator,
                playerPanelSection.horizontalSeparators,
                numberOfPlayers);

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension contentSize = frame.getContentPane().getSize();
                updateScaleForFrameSize(contentSize.width, contentSize.height);
                applyScaledLayout(
                    boardGridSection.goPanel,
                    boardGridSection.jailPanel,
                    boardGridSection.freeParkingPanel,
                    boardGridSection.goToJailPanel,
                    boardGridSection.topRow,
                    boardGridSection.bottomRow,
                    boardGridSection.leftColumn,
                    boardGridSection.rightColumn,
                        centerPanel,
                        dicePanel,
                        rollDiceButton,
                        buttonPanel,
                    playerPanelSection.playerInfoPanels,
                    playerPanelSection.verticalSeparator,
                    playerPanelSection.horizontalSeparators,
                        numberOfPlayers);
                boardPanel.revalidate();
                boardPanel.repaint();
            }
        });
    }

    private static JLabel createPlayerToken(Tokens token, Color color) {
        // Create a JLabel to represent the token
        JLabel tokenLabel = new JLabel(token.toString()) {
            @Override
            protected void paintComponent(Graphics g) {
                int tokenSize = Math.min(getWidth(), getHeight());

                // Draw a circle with the specified color
                g.setColor(color);
                g.fillOval(0, 0, tokenSize, tokenSize);

                // Draw the token text in the center of the circle
                g.setColor(Color.WHITE); // Text color
                g.setFont(new Font("Arial", Font.BOLD, Math.max(12, s(16))));
                FontMetrics fm = g.getFontMetrics();
                String text = token.toString();
                int textWidth = fm.stringWidth(text);
                int textHeight = fm.getAscent();
                int x = (tokenSize - textWidth) / 2; // Center horizontally
                int y = (tokenSize - textHeight) / 2 + fm.getAscent(); // Center vertically
                g.drawString(text, x, y);
            }
        };

        // Set the initial token size for positioning on the board.
        tokenLabel.setSize(s(40), s(40));
        tokenLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tokenLabel.setVerticalAlignment(SwingConstants.CENTER);

        // Add a tooltip with the token name
        tokenLabel.setToolTipText(token.toString());

        return tokenLabel;
    }

    private static void positionToken(JLabel token, int position) {
        token.setLocation(s(BoardData.X_COORDINATES[position]), s(BoardData.Y_COORDINATES[position]));
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
        for (int i = 0; i < playerPanels.length; i++) {
            JPanel panel = playerPanels[i];
            if (players != null && i < players.length && players[i] != null && !players[i].isActive()) {
                panel.setBackground(new Color(230, 230, 230));
            } else {
                panel.setBackground(Color.LIGHT_GRAY);
            }
        }

        // Highlight current player's panel
        if (playerIndex >= 0 && playerIndex < playerPanels.length) {
            playerPanels[playerIndex].setBackground(new Color(200, 230, 200)); // Light green highlight
        }

        playerPanel.revalidate();
        playerPanel.repaint();
    }

    private static boolean isPlayerBankrupt(Player player) {
        return GameRules.isBankrupt(player);
    }

    private static void eliminatePlayer(Player player, JFrame frame) {
        player.setActive(false);

        if (gameSpaces != null) {
            for (Space space : gameSpaces) {
                if (space.getOwner() == player) {
                    space.setOwner(null);
                }
            }
        }

        int bankruptIndex = getPlayerIndex(player);
        if (bankruptIndex >= 0 && bankruptIndex < playerTokens.length) {
            playerTokens[bankruptIndex].setVisible(false);
        }

        JOptionPane.showMessageDialog(frame,
                player.getName() + " is bankrupt and eliminated from the game.",
                "Bankruptcy",
                JOptionPane.WARNING_MESSAGE);
    }

    private static void announceWinnerAndDisableRoll(Player winner, JFrame frame, JButton rollDiceButton) {
        rollDiceButton.setEnabled(false);
        JOptionPane.showMessageDialog(frame,
                winner.getName() + " wins the game!",
                "Game Over",
                JOptionPane.INFORMATION_MESSAGE);
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
        if (playerIndex >= 0 && playerIndex < BoardData.TOKEN_COLORS.length) {
            return BoardData.TOKEN_COLORS[playerIndex];
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

        // Show the drawn card
        JOptionPane.showMessageDialog(frame,
                drawnCard,
                "Chance Card",
            JOptionPane.INFORMATION_MESSAGE);

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

        // Show the drawn card
        JOptionPane.showMessageDialog(frame,
                drawnCard,
                "Community Chest Card",
            JOptionPane.INFORMATION_MESSAGE);

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

}
