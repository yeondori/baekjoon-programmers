import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static final int MAX = Integer.MAX_VALUE, CYCLE = 100;
    static int[] coins = new int[]{1, 10, 25};
    static int[] dp = new int[CYCLE];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine().trim());

        for (int price = 1; price < CYCLE; price++) {
            dp[price] =  MAX;

            for (int coin : coins) {
                if (price - coin >= 0) {
                    dp[price] = Math.min(dp[price], dp[price - coin] + 1);
                }
            }
        }

        long price;
        for (int i = 0; i < T; i++) {
            price = Long.parseLong(br.readLine().trim());
            answer.append(solve(price)).append("\n");
        }
        System.out.println(answer);
    }

    private static long solve(long price) {
        int cnt = 0;

        while (price > 0) {
            int remain = Math.toIntExact(price % 100);
            cnt += dp[remain];
            price /= 100;
        }
        return cnt;
    }
}
