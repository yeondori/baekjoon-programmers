import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int[] values = new int[N];
        for (int i = 0; i < N; i++) {
            values[i] = scanner.nextInt();
        }
        scanner.close();

        int[][] dp = new int[N][N];

        for (int len = 1; len <= N; len++) {
            for (int left = 0; left + len - 1 < N; left++) {
                int right = left + len - 1;
                int k = N - (right - left);
                if (left == right) {
                    dp[left][right] = values[left] * k;
                } else {
                    dp[left][right] = Math.max(
                            values[left] * k + dp[left + 1][right],
                            values[right] * k + dp[left][right - 1]
                    );
                }
            }
        }

        System.out.println(dp[0][N - 1]);
    }
}
