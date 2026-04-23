public final class GameRules {
    private GameRules() {
    }

    public static boolean isBankrupt(Player player) {
        return player != null && player.getWallet() < 0;
    }

    public static int countActivePlayers(Player[] players) {
        if (players == null) {
            return 0;
        }

        int count = 0;
        for (Player player : players) {
            if (player != null && player.isActive()) {
                count++;
            }
        }
        return count;
    }

    public static Player findWinner(Player[] players) {
        if (countActivePlayers(players) != 1) {
            return null;
        }

        for (Player player : players) {
            if (player != null && player.isActive()) {
                return player;
            }
        }
        return null;
    }

    public static int getNextActivePlayerIndex(Player[] players, int currentIndex) {
        if (players == null || players.length == 0) {
            return -1;
        }

        for (int offset = 1; offset <= players.length; offset++) {
            int candidate = (currentIndex + offset) % players.length;
            Player player = players[candidate];
            if (player != null && player.isActive()) {
                return candidate;
            }
        }

        return -1;
    }
}
