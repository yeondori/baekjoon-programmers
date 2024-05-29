import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int Size, CowNum, PathNum;

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Path {
        int x1, y1, x2, y2;

        public Path(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Path path = (Path) o;
            return x1 == path.x1 && y1 == path.y1 && x2 == path.x2 && y2 == path.y2;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x1, y1, x2, y2);
        }
    }

    static int possNum;
    static boolean[][] board;
    static Path curPath;
    static Map<Path, Boolean> allPath;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Size = Integer.parseInt(st.nextToken());
        CowNum = Integer.parseInt(st.nextToken());
        PathNum = Integer.parseInt(st.nextToken());

        board = new boolean[Size + 1][Size + 1];
        allPath = new HashMap<>();

        for (int i = 0; i < PathNum; i++) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            allPath.put(new Path(x1, y1, x2, y2), true);
            allPath.put(new Path(x2, y2, x1, y1), true);
        }

        for (int i = 0; i < CowNum; i++) {
            st = new StringTokenizer(br.readLine());

            int cowX = Integer.parseInt(st.nextToken());
            int cowY = Integer.parseInt(st.nextToken());

            board[cowX][cowY] = true;
        }

        solve();
    }

    private static void solve() {
        possNum = 0;
        curPath = new Path(0,0,0,0);
        for (int i = 1; i <= Size; i++) {
            for (int j = 1; j <= Size; j++) {
                if (board[i][j]) {
                    bfs(i, j);
                }
            }
        }

        System.out.println(CowNum * (CowNum - 1) / 2 - possNum / 2);
    }

    private static void bfs(int i, int j) {
        ArrayDeque<Point> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[Size + 1][Size + 1];

        q.add(new Point(i, j));
        visited[i][j] = true;

        int nx, ny;
        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int k = 0; k < 4; k++) {
                nx = cur.x + dx[k];
                ny = cur.y + dy[k];

                if (outOfRange(nx, ny) || visited[nx][ny]) {
                    continue;
                }

                curPath.x1 = cur.x;
                curPath.y1 = cur.y;
                curPath.x2 = nx;
                curPath.y2 = ny;

                if (allPath.get(curPath) != null) {
                    continue;
                }

                if (board[nx][ny]) {
                    possNum++;
                }

                visited[nx][ny] = true;
                q.add(new Point(nx, ny));
            }
        }
    }

    private static boolean outOfRange(int x, int y) {
        return x < 1 || x > Size || y < 1 || y > Size;
    }
}
