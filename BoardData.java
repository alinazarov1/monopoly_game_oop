import java.awt.Color;

public final class BoardData {
    public static final int BOARD_SIZE = 40;

    public static final int[] X_COORDINATES = {
            748, 678, 608, 538, 468, 398, 328, 258, 188, 118, 52,
            52, 52, 52, 52, 52, 52, 52, 52, 52, 52,
            52, 118, 188, 258, 328, 398, 468, 538, 608, 678,
            748, 748, 748, 748, 748, 748, 748, 748, 748, 748
    };

    public static final int[] Y_COORDINATES = {
            724, 724, 724, 724, 724, 724, 724, 724, 724, 724, 724,
            654, 584, 514, 444, 374, 304, 234, 164, 94, 28,
            28, 28, 28, 28, 28, 28, 28, 28, 28, 28,
            94, 164, 234, 304, 374, 444, 514, 584, 654, 724
    };

    public static final Color[] TOKEN_COLORS = {
            Color.RED,
            Color.BLUE,
            Color.GREEN,
            Color.ORANGE,
            Color.MAGENTA,
            Color.CYAN,
            Color.PINK,
            new Color(12, 34, 54)
    };

    public static final Tokens[] TOKENS = {
            Tokens.A, Tokens.B, Tokens.C, Tokens.D,
            Tokens.E, Tokens.G, Tokens.H, Tokens.I
    };

    private BoardData() {
    }

    public static Space[] createSpaces() {
        Space[] spaces = new Space[BOARD_SIZE];

        spaces[0] = new Space("GO", "Corner", "Collect $200 as you pass GO.");
        spaces[1] = new Space("MEDITERRANEAN AVENUE", "Property", "Price: $60, Rent: $15");
        spaces[2] = new Space("COMMUNITY CHEST", "Community Chest", "Draw a card from the deck.");
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
        spaces[17] = new Space("COMMUNITY CHEST", "Community Chest", "Draw a card from the deck.");
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
        spaces[33] = new Space("COMMUNITY CHEST", "Community Chest", "Draw a card from the deck.");
        spaces[34] = new Space("PENNSYLVANIA AVENUE", "Property", "Price: $320, Rent: $80");
        spaces[35] = new Space("SHORT LINE", "Railroad", "Price: $200, Rent: $50");
        spaces[36] = new Space("CHANCE", "Chance", "Draw a card from the deck.");
        spaces[37] = new Space("PARK PLACE", "Property", "Price: $350, Rent: $85");
        spaces[38] = new Space("LUXURY TAX", "Tax", "Pay $100 tax.");
        spaces[39] = new Space("BOARDWALK", "Property", "Price: $400, Rent: $100");

        return spaces;
    }
}
