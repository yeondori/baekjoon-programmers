import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int MIN_CYCLE = 4;
    static boolean find;
    static int ROW, COL;
    static char[][] board;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static Point end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        ROW = Integer.parseInt(st.nextToken());
        COL = Integer.parseInt(st.nextToken());
        board = new char[ROW][COL];
        visited = new boolean[ROW][COL];

        for (int r = 0; r < ROW; r++) {
            board[r] = br.readLine().toCharArray();
        }

        findCycle();
    }

    private static void findCycle() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                end = new Point(i, j);
                // 방문 배열 초기화
                for (int k = 0; k < ROW; k++) {
                    Arrays.fill(visited[k], false);
                }
                visited[i][j] = true;
                dfs(i, j, board[i][j], 1);
                if (find) {
                    System.out.println("Yes");
                    return;
                }
            }
        }
        System.out.println("No");
    }

    private static void dfs(int x, int y, char sep, int cnt) {
        if (find) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (outOfRange(nx, ny)) {
                continue;
            }

            if (nx==end.x && ny== end.y && cnt >= MIN_CYCLE) {
                find = true;
                return;
            }

            if (visited[nx][ny] || board[nx][ny] != sep) {
                continue;
            }

            visited[nx][ny] = true;
            dfs(nx, ny, sep, cnt + 1);
        }
    }

    private static boolean outOfRange(int x, int y) {
        return x < 0 || x >= ROW || y < 0 || y >= COL;
    }
}
