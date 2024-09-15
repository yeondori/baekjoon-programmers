import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static final char WIN = 'O', LOSE = 'X';
    static StringBuilder Cards;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        Cards = new StringBuilder("");
        for (int i = 0, end = 2 * n; i < end; i++) {
            Cards.append(st.nextToken());
        }

        System.out.println(solve());
    }

    static int solve() {
        Queue<StringBuilder> q = new ArrayDeque<>();
        Set<String> visit = new HashSet<>();

        q.add(Cards);
        int cnt = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                StringBuilder curCards = q.poll();

                if (isWin(curCards.toString())) {
                    return cnt;
                }

                for (int idx = 0, end = 2 * n; idx < end; idx++) {
                    StringBuilder switchCard = switchCard(curCards, idx);

                    String nextCard = switchCard.toString();
                    if (!visit.contains(nextCard)) {
                        q.add(switchCard);
                        visit.add(nextCard);
                    }
                }
            }
            cnt++;
        }
        return -1;
    }

    static StringBuilder switchCard(StringBuilder curCards, int idx) {
        StringBuilder switchCards = new StringBuilder(curCards.toString());
        char temp = switchCards.charAt(idx);
        switchCards.deleteCharAt(idx);
        switchCards.insert(0, temp);

        return switchCards;
    }

    static boolean isWin(String cards) {
        int myScore = 0, yourScore = 0;

        for (int i = 0, end = 2 * n; i < end; i+=2) {
            char myCard = cards.charAt(i);
            char yourCard = cards.charAt(i+1);

            if (myCard == yourCard) {
                continue;
            }

            if (myCard == WIN) {
                myScore++;
            } else if (myCard == LOSE) {
                yourScore++;
            } 
        }

        return myScore > yourScore;
    }
}
