import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] stairs = new int[N];
        for (int i = 0; i < N; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(getMaxScore(stairs));
    }

    private static int getMaxScore(int[] stairs) {
        int size = stairs.length;

        if (size == 0) {
            return 0;
        } else if (size == 1) {
            return stairs[0];
        } else if (size == 2) {
            return stairs[0] + stairs[1];
        }

        int[] dp = new int[size];

        dp[0] = stairs[0];
        dp[1] = Math.max(stairs[0] + stairs[1], stairs[1]);
        dp[2] = Math.max(stairs[0] + stairs[2], stairs[1] + stairs[2]);

        for (int i = 3; i < size; i++) {
            dp[i] = Math.max(dp[i - 2] + stairs[i], dp[i - 3] + stairs[i - 1] + stairs[i]);
        }

        // 마지막 계단은 반드시 밟아야 하므로, dp[size - 1] 반환합니다.
        return dp[size - 1];
    }
}