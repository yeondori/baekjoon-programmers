import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int[] steps;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = input.length-1;
        steps = new int[N];
        dp = new int[N][5][5];
        for (int i = 0; i < N; i++) {
            steps[i] = Integer.parseInt(input[i]);
        }

        int answer = go(0, 0, 0);
        System.out.println(answer);
    }

    private static int go(int idx, int left, int right) {
        if (idx == N) {
            return 0;
        }

        if (dp[idx][left][right] != 0) {
            return dp[idx][left][right];
        }

        int next = steps[idx];
        dp[idx][left][right] = Math.min(getPower(left, next) + go(idx + 1, next, right),
                getPower(right, next) + go(idx + 1, left, next));
        return dp[idx][left][right];
    }

    static int getPower(int cur, int next) {
        int diff = Math.abs(cur - next);
        if (cur == 0) {
            return 2;
        } else if (diff == 0) {
            return 1;
        } else if (diff == 2) {
            return 4;
        } else {
            return 3;
        }
    }
}
