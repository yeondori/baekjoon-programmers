import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        int[] heights = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            heights[i] = sc.nextInt();
        }

        List<Integer>[] adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int X = sc.nextInt();
            int Y = sc.nextInt();
            adj[X].add(Y);
            adj[Y].add(X);
        }

        int[][] dp = new int[N + 1][501];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dp[i], INF);
            dp[i][0] = heights[i];
        }

        for (int k = 1; k <= 500; k++) {
            for (int u = 1; u <= N; u++) {
                for (int v : adj[u]) {
                    dp[v][k] = Math.min(dp[v][k], dp[u][k - 1]);
                }
            }
        }

        int T = sc.nextInt();
        for (int t = 0; t < T; t++) {
            int A = sc.nextInt();
            int K = sc.nextInt();

            int result = dp[A][K];
            System.out.println(result == INF ? -1 : result);
        }

        sc.close();
    }
}
