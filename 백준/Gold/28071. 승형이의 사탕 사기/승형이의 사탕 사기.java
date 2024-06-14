import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


// https://pingu0130.tistory.com/m/61
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] candies = new int[N];
        int[][] dp = new int[M + 1][K];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            candies[i] = Integer.parseInt(st.nextToken());
            dp[1][candies[i] % K] = Math.max(candies[i], dp[1][candies[i] % K]);
        }

        dp[0][0] = 0;

        // DP 계산
        for (int i = 1; i < M; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < K; k++) {
                    if (dp[i][Math.floorMod(k - candies[j], K)] != 0) {
                        dp[i + 1][k] = Math.max(dp[i + 1][k], dp[i][Math.floorMod(k - candies[j], K)] + candies[j]);
                    }
                }
            }
        }

        // 결과 찾기
        int maxCandies = 0;
        for (int i = 0; i <= M; i++) {
            if (dp[i][0] != -1) {
                maxCandies = Math.max(maxCandies, dp[i][0]);
            }
        }

        System.out.println(maxCandies);
    }
}
