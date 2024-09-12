import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int NOT_YET = 0, RIPE = 1, EMPTY = -1;

    static int row, col, days, leftTomatoes;
    static int[][] tomatoes;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static Queue<Point> q;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        tomatoes = new int[row][col];

        leftTomatoes = 0;
        q = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                tomatoes[i][j] = Integer.parseInt(st.nextToken());

                if (tomatoes[i][j] == NOT_YET) {
                    leftTomatoes++;
                } else if (tomatoes[i][j] == RIPE) {
                    q.add(new Point(i, j));
                }
            }
        }

        solve();
    }

    static void solve() {
        boolean[][] visited = new boolean[row][col];

        if (leftTomatoes == 0) {
            System.out.println(0);
            return;
        }

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Point cur = q.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = cur.x + dx[j];
                    int ny = cur.y + dy[j];

                    if (outOfRange(nx, ny) || visited[nx][ny]) continue;

                    if (tomatoes[nx][ny] == NOT_YET) {
                        tomatoes[nx][ny] = RIPE;
                        leftTomatoes--;
                        q.add(new Point(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }
            days++;
        }

        System.out.println(leftTomatoes == 0? days - 1:-1);
    }

    static boolean outOfRange(int x, int y) {
        return x < 0 || x >= row || y < 0 || y >= col;
    }
}
