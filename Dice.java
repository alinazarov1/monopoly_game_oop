import java.awt.*;
import java.util.Random;

public class Dice {
    private int dice1;
    private int dice2;
    private boolean isDouble;
    private int consecutiveDoubles;

    public Dice() {
        rollDice();
        consecutiveDoubles = 0;
    }

    // Roll the dice to get random values between 1 and 6
    public void rollDice() {
        Random random = new Random();
        dice1 = random.nextInt(6) + 1;
        dice2 = random.nextInt(6) + 1;

        // Check if it's a double
        isDouble = (dice1 == dice2);

        // Update consecutive doubles counter
        if (isDouble) {
            consecutiveDoubles++;
        } else {
            consecutiveDoubles = 0;
        }
    }

    // Return the dice values
    public int getDice1() {
        return dice1;
    }

    public int getDice2() {
        return dice2;
    }

    // Get total of both dice
    public int getTotal() {
        return dice1 + dice2;
    }

    // Check if current roll is a double
    public boolean isDouble() {
        return isDouble;
    }

    // Get number of consecutive doubles
    public int getConsecutiveDoubles() {
        return consecutiveDoubles;
    }

    // Reset consecutive doubles counter
    public void resetConsecutiveDoubles() {
        consecutiveDoubles = 0;
    }

    // Method to update dice values (re-roll the dice)
    public void updateDice() {
        rollDice();
    }

    // Draw the dice on the provided graphics object
    public void draw(Graphics g, int x, int y) {
        int[][] positions = {
                {}, // Empty for index 0
                { 40, 40 }, // 1
                { 20, 20, 60, 60 }, // 2
                { 20, 20, 40, 40, 60, 60 }, // 3
                { 20, 20, 60, 20, 20, 60, 60, 60 }, // 4
                { 20, 20, 60, 20, 40, 40, 20, 60, 60, 60 }, // 5
                { 20, 20, 60, 20, 20, 40, 60, 40, 20, 60, 60, 60 } // 6
        };

        // Draw the first dice
        g.setColor(Color.WHITE);
        g.fillRoundRect(x, y, 80, 80, 20, 20);
        g.setColor(Color.BLACK);
        g.drawRoundRect(x, y, 80, 80, 20, 20);

        int[] dots = positions[dice1];
        for (int i = 0; i < dots.length; i += 2) {
            g.fillOval(x + dots[i], y + dots[i + 1], 10, 10);
        }

        // Draw the second dice
        g.setColor(Color.WHITE);
        g.fillRoundRect(x + 100, y, 80, 80, 20, 20);
        g.setColor(Color.BLACK);
        g.drawRoundRect(x + 100, y, 80, 80, 20, 20);

        int[] dots2 = positions[dice2];
        for (int i = 0; i < dots2.length; i += 2) {
            g.fillOval(x + 100 + dots2[i], y + dots2[i + 1], 10, 10);
        }

        // Highlight doubles with a green border if rolled
        if (isDouble) {
            g.setColor(Color.GREEN);
            g.drawRoundRect(x - 5, y - 5, 190, 90, 20, 20);
        }
    }
}