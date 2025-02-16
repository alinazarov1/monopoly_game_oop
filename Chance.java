import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Chance {
    private List<String> chanceCards;
    private int currentCardIndex;

    public Chance() {
        chanceCards = new ArrayList<>();
        initializeCards();
        shufflechCards();
    }

    private void initializeCards() {
        chanceCards.add("Advance to Go (Collect $200)");
        chanceCards.add("Bank pays you a dividend of $50");
        chanceCards.add("Go directly to Jail – Do not pass Go, do not collect $200");
        chanceCards.add("Your building loan matures – Collect $150");
        chanceCards.add("Get out of Jail Free – This card may be kept until needed or sold");
        chanceCards.add("Advance to Illinois Avenue");
        chanceCards.add("Advance to St. Charles Place");
        chanceCards.add("Advance token to nearest Utility. If unowned, you may buy it.");
        chanceCards.add("Advance token to nearest Railroad and pay owner twice the normal rent.");
        chanceCards.add("Pay poor tax of $15");
        chanceCards.add("Take a trip to Reading Railroad");
        chanceCards.add("Advance to Boardwalk");
        chanceCards.add("You have won a crossword competition – Collect $100");
    }

    private void shufflechCards() {
        Collections.shuffle(chanceCards);
        currentCardIndex = 0;
    }

    public String drawchCard() {
        if (currentCardIndex >= chanceCards.size()) {
            shufflechCards();
        }
        return chanceCards.get(currentCardIndex++);
    }
}
