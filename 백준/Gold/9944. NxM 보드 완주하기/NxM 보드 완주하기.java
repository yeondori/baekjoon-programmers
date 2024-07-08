import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int MAX_VALUE = 1_000_000;
    static int[][] board;
    static int N, M;
    static int blankNum, answer;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        int num = 1;

        while ((input = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(input);

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            board = new int[N][M];
            visited = new boolean[N][M];
            blankNum = 0;

            for (int i = 0; i < N; i++) {
                input = br.readLine();
                for (int j = 0; j < M; j++) {
                    if (input.charAt(j) == '*') {
                        board[i][j] = -1;  // -1 represents obstacle
                    } else {
                        board[i][j] = 0;   // 0 represents empty space
                        blankNum++;
                    }
                }
            }

            answer = MAX_VALUE;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (board[i][j] == 0) {  // Start DFS from an empty space
                        visited = new boolean[N][M];
                        visited[i][j] = true;
                        dfs(i, j, 1, 0);
                    }
                }
            }

            if (answer == MAX_VALUE) {
                answer = -1;
            }

            System.out.println("Case " + num + ": " + answer);
            num++;
        }

        br.close();
    }

    private static void dfs(int x, int y, int visitedCount, int moveCount) {
        if (moveCount > answer) {
            return;
        }
        
        if (visitedCount == blankNum) {
            answer = Math.min(answer, moveCount);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x, ny = y, steps = 0;
            while (true) {
                int nextX = nx + dx[i];
                int nextY = ny + dy[i];

                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M || board[nextX][nextY] == -1 || visited[nextX][nextY]) {
                    break;
                }

                nx = nextX;
                ny = nextY;
                steps++;
            }

            if (steps > 0) {
                int pathX = x, pathY = y;
                while (!(pathX == nx && pathY == ny)) {
                    pathX += dx[i];
                    pathY += dy[i];
                    visited[pathX][pathY] = true;
                }

                dfs(nx, ny, visitedCount + steps, moveCount + 1);

                pathX = x;
                pathY = y;
                while (!(pathX == nx && pathY == ny)) {
                    pathX += dx[i];
                    pathY += dy[i];
                    visited[pathX][pathY] = false;
                }
            }
        }
    }
}
