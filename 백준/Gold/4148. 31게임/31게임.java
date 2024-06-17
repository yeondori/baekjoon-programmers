import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Map<GameState, Boolean> cache = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                determineWinner(input);
            }
        }

        scanner.close();
    }

    private static void determineWinner(String input) {
        int[] used = new int[7];
        int cardSum = 0;

        for (char c : input.toCharArray()) {
            int cardValue = Character.getNumericValue(c);
            used[cardValue]++;
            cardSum += cardValue;
        }

        char player = input.length() % 2 == 0 ? 'A' : 'B';
        char opponent = input.length() % 2 == 0 ? 'B' : 'A';

        boolean isWinningPosition = isWinningPosition(used, cardSum);
        System.out.println(input + " " + (isWinningPosition ? player : opponent));
    }

    private static boolean isWinningPosition(int[] used, int cardSum) {
        GameState state = new GameState(used, cardSum);

        if (cache.containsKey(state)) {
            return cache.get(state);
        }

        for (int i = 1; i <= 6; i++) {
            if (cardSum + i <= 31 && used[i] < 4) {
                int[] newUsed = used.clone();
                newUsed[i]++;
                if (!isWinningPosition(newUsed, cardSum + i)) {
                    cache.put(state, true);
                    return true;
                }
            }
        }

        cache.put(state, false);
        return false;
    }
}

class GameState {
    private final int[] used;
    private final int cardSum;

    public GameState(int[] used, int cardSum) {
        this.used = used.clone(); // Defensive copy
        this.cardSum = cardSum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameState gameState = (GameState) o;
        if (cardSum != gameState.cardSum) return false;
        for (int i = 1; i < used.length; i++) {
            if (used[i] != gameState.used[i]) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = cardSum;
        for (int i = 1; i < used.length; i++) {
            result = 31 * result + used[i];
        }
        return result;
    }
}
