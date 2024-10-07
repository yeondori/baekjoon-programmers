import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {

    static int n, m;
    static int[][] candy;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            if (n == 0) break;

            candy = new int[n][m];
            dp = new int[n][m][2];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    candy[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int[][] row : dp) {
                for (int[] col : row) {
                    Arrays.fill(col, -1);
                }
            }

            System.out.println(solve(0, 0, 0));
        }
    }

    static int solve(int x, int y, int willJump) {
        if (y >= m) return solve(x + 1 + willJump, 0, 0);

        if (x >= n) return 0;
        
        if (dp[x][y][willJump] != -1) return dp[x][y][willJump];

        int noPick = solve(x, y + 1, willJump);
        int pick = candy[x][y] + solve(x, y + 2, 1);

        dp[x][y][willJump] = Math.max(noPick, pick);
        return dp[x][y][willJump];
    }
}
