import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    
    static int row, col, diagNum;
    static char[][] board;
    static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        diagNum = Integer.parseInt(st.nextToken());

        board= new char[row][col];
        for (int i = 0; i < row; i++) {
            board[i] = br.readLine().toCharArray();
        }

        init();
        solve();
    }

    static void solve() throws IOException {
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            System.out.println(dp[x][y] ? "First" : "Second");
        }
    }
    
    static void init() {
        dp = new boolean[row][col];

        for (int i = row-1; i >= 0; i--) {
            for (int j = col-1; j >= 0; j--) {
                if (board[i][j] == '#' || dp[i][j]) continue;
                if (inRange(i, j - 1)) {
                    dp[i][j-1] = true;
                }
                if (inRange(i - 1, j)) {
                    dp[i-1][j] = true;
                }

                for (int k = 1; k <= diagNum; k++) {
                    if (inRange(i - k, j - k)) {
                        dp[i-k][j-k] = true;
                    } else {
                        break;
                    }
                }
            }
        }
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && x < row && y >= 0 && y < col;
    }
}
