
public class Player {
    private String name;
    private int wallet;
    private int position;
    private int getOutOfJailFreeCards = 0;

    enum Token {
        A, B, C, D, E, G, H, I
    }

    public Player(String name) {
        this.name = name;
        this.wallet = 1500; // Starting money
        this.position = 0;
    }

    // Remove the getToken() method since we'll use names instead
    public String getName() {
        return name;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int amount) {
        this.wallet = amount;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void addGetOutOfJailFreeCard() {
        getOutOfJailFreeCards++;
    }

    public int getGetOutOfJailFreeCards() {
        return getOutOfJailFreeCards;
    }

}
