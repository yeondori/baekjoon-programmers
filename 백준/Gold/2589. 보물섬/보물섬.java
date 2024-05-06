import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
    static int Row, Col;
    static char[][] board;

    static class Point {
        int x, y, cnt;

        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    static List<Point> lands;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        Row = Integer.parseInt(input[0]);
        Col = Integer.parseInt(input[1]);

        board = new char[Row][Col];
        lands = new ArrayList<>();
        for (int i = 0; i < Row; i++) {
            input = br.readLine().split("");
            for (int j = 0; j < Col; j++) {
                board[i][j] = input[j].charAt(0);

                if (board[i][j] == 'L') {
                    lands.add(new Point(i, j, 0));
                }
            }
        }
        solve();
    }

    private static void solve() {
        int size = lands.size();
        int maxDist = 0;
        for (Point str : lands) {
            int dist = bfs(str);
            if (dist > maxDist) {
                maxDist = dist;
            }
        }
        System.out.println(maxDist);
    }

    private static int bfs(Point str) {
        boolean[][] visited = new boolean[Row][Col];
        Queue<Point> q = new ArrayDeque<>();

        q.add(new Point(str.x, str.y, 1));
        visited[str.x][str.y] = true;

        int cnt = 0;
        while (!q.isEmpty()) {
            Point cur = q.poll();
            cnt = cur.cnt;
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (inBound(nx, ny) && !visited[nx][ny] && board[nx][ny] == 'L') {
                    visited[nx][ny] = true;
                    q.add(new Point(nx, ny, cur.cnt + 1));
                }
            }
        }
        return cnt - 1;
    }

    private static boolean inBound(int x, int y) {
        return x >= 0 && x < Row && y >= 0 && y < Col;
    }
}
