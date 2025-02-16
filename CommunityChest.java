import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CommunityChest {
    final List<String> cards;

    public CommunityChest() {
        cards = new ArrayList<>();
        // Add Community Chest cards to the list
        cards.add("Bank error in your favor. Collect $200.");
        cards.add("You have been called to the doctor for a check-up. Collect $50.");
        cards.add("Your holiday fund matures. Collect $100.");
        cards.add("You receive a Get Out of Jail Free card. This card may be kept until needed or sold.");
        cards.add("Your life insurance has matured. Collect $100.");
        cards.add("Collect $150 from each player.");
        cards.add("You have won first prize in a beauty contest. Collect $100.");
        cards.add("Pay for your friend's hospital treatment. Pay $50.");
        cards.add("Pay school fees of $50.");
        cards.add("Pay your doctor's bills. Pay $100.");
        cards.add("Go directly to Jail. Do not pass GO, do not collect $200.");
        cards.add(
                "You have to pay for repairs on your properties. Pay $40 for each house you own, or $115 for each hotel.");
        cards.add("Street repairs: Pay $25 per house, $100 per hotel you own.");
        cards.add("Pay taxes due: $100.");
        cards.add("Pay $150 in legal fees.");
        cards.add("Advance to the nearest railroad and pay the owner rent based on the dice roll.");
        cards.add("Advance to GO. Collect $200.");
        cards.add("Pay a fine of $20.");
        cards.add("Take a loan of $150. Pay back with 10% interest.");
        cards.add("You are required to sell property. Choose one property to sell to the bank for $100.");
    }

    public String drawCard() {
        // Shuffle the cards to ensure randomness
        Collections.shuffle(cards);

        // Randomly select a card after shuffling
        Random random = new Random();
        int index = random.nextInt(cards.size());

        return cards.get(index);
    }

}
