import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int ROW = 10, COL = 9;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static int[] ddx = {-1, -1, 1, 1};
    static int[] ddy = {1, -1, -1, 1};

    // -1, 0 -> (-1, -1), (-1, 1)
    // 0, -1 -> (-1, -1), (1, -1)
    // 1, 0 -> (1, -1), (1, 1)
    // 0, 1 -> (-1, 1), (1, 1)


    static int kingX, kingY, sangX, sangY, ddx1, ddy1, ddx2, ddy2;

    static class Point {
        int x, y, cnt;

        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        sangX = Integer.parseInt(st.nextToken());
        sangY = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        kingX = Integer.parseInt(st.nextToken());
        kingY = Integer.parseInt(st.nextToken());

        solve();
    }

    static void solve() {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[ROW][COL];

        q.add(new Point(sangX, sangY, 0));
        visited[sangX][sangY] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            if (cur.x == kingX && cur.y == kingY) {
                System.out.println(cur.cnt);
                break;
            }

            for (int i = 0; i < 4; i++) {

                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (canMove(nx, ny)) {
                    for (int j = 0; j < 2; j++) {
                        int idx = (i + j) % 4;
                        int nnx = nx + ddx[idx];
                        int nny = ny+ ddy[idx];

                        if (canMove(nnx, nny)) {
                            nnx += ddx[idx];
                            nny += ddy[idx];

                            if (!outOfRange(nnx, nny) && !visited[nnx][nny]) {
                                visited[nnx][nny] = true;
                                q.add(new Point(nnx, nny, cur.cnt + 1));
                            }
                        }
                    }
                }
            }
        }
    }

    static boolean outOfRange (int x, int y) {
        return x < 0 || x >= ROW || y < 0 || y >= COL;
    }

    static boolean canMove(int x, int y) {
        if (outOfRange(x, y)) {
            return false;
        }
        return (kingX != x || kingY != y);
    }
}
