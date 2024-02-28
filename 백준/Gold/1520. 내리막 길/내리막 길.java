import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int M, N;
    static int[][] board;
    static int[][] dp;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        board = new int[M][N];
        dp = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(0, 0));
    }

    private static int dfs(int x, int y) {
        if (x == M - 1 && y == N - 1) {
            return 1; // 경로를 찾은 경우 1 반환
        }

        if (dp[x][y] != -1) {
            return dp[x][y]; // 이미 계산한 경우 저장된 값을 반환
        }

        dp[x][y] = 0; // 현재 위치에서의 경로 수 초기화

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= M || ny < 0 || ny >= N) {
                continue;
            }

            if (board[nx][ny] < board[x][y]) {
                dp[x][y] += dfs(nx, ny); // 내리막 길인 경우 DFS로 다음 위치로 이동하여 경로 수 누적
            }
        }

        return dp[x][y];
    }
}
