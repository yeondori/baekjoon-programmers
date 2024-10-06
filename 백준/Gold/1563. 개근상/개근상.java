import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static final int MOD = 1_000_000, LATE_COUNT = 2, ABSENT_COUNT = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int days = Integer.parseInt(br.readLine());

        System.out.println(solve(days));
    }

    static int solve(int days) {
        int[][][] dp = new int[days+1][LATE_COUNT][ABSENT_COUNT];

        dp[1][0][0] = 1;
        dp[1][1][0] = 1;
        dp[1][0][1] = 1;

        for (int day = 2; day <= days; day++) {
            dp[day][0][0] = (dp[day-1][0][0] + dp[day-1][0][1] + dp[day-1][0][2]) % MOD;
            dp[day][0][1] = dp[day-1][0][0] % MOD;
            dp[day][0][2] = dp[day-1][0][1] % MOD;
            dp[day][1][0] = (dp[day-1][0][0] + dp[day-1][0][1] + dp[day-1][0][2] +
                            dp[day-1][1][0] + dp[day-1][1][1] + dp[day-1][1][2]) % MOD;
            dp[day][1][1] = dp[day-1][1][0] % MOD;
            dp[day][1][2] = dp[day-1][1][1] % MOD;

        }

        return (dp[days][0][0] + dp[days][0][1] + dp[days][0][2] +
                dp[days][1][0] + dp[days][1][1] + dp[days][1][2]) % MOD;
    }
}
