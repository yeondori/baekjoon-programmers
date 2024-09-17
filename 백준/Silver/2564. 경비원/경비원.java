import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int row, col, storeNum;

    static class Point {
        int x, y, cnt;

        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    static Point pos;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static Point[] stores;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());

        storeNum = Integer.parseInt(br.readLine());
        stores = new Point[storeNum];
        int dir, dist;
        for (int i = 0; i < storeNum; i++) {
            st = new StringTokenizer(br.readLine());

            dir = Integer.parseInt(st.nextToken());
            dist = Integer.parseInt(st.nextToken());

            stores[i] = getPoint(dir, dist);
        }

        st = new StringTokenizer(br.readLine());
        dir = Integer.parseInt(st.nextToken());
        dist = Integer.parseInt(st.nextToken());
        pos = getPoint(dir, dist);

        solve();
    }

    static Point getPoint(int direction, int distance) {
        switch (direction) {
            case 1:
                return new Point(0, distance, 0);
            case 2:
                return new Point(row, distance, 0);
            case 3:
                return new Point(distance, 0, 0);
            case 4:
                return new Point(distance, col, 0);
        }
        return null;
    }

    static void solve() {
        int cnt = 0;
        for (int i = 0; i < storeNum; i++) {
            cnt += bfs(stores[i]);
        }
        System.out.println(cnt);
    }

    static int bfs(Point point) {
        Queue<Point> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[row + 1][col + 1];

        visited[pos.x][pos.y] = true;
        q.add(new Point(pos.x, pos.y, 0));

        while (!q.isEmpty()) {
            Point cur = q.poll();

            if (cur.x == point.x && cur.y == point.y) {
                return cur.cnt;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (outOfRange(nx, ny) || visited[nx][ny] || outOfBorder(nx, ny)) continue;

                visited[nx][ny] = true;
                q.add(new Point(nx, ny, cur.cnt + 1));
            }

        }
        return 0;
    }

    static boolean outOfRange(int x, int y) {
        return x < 0 || x > row || y < 0 || y > col;
    }

    static boolean outOfBorder(int x, int y) {
        return x != 0 && x != row && y != 0 && y != col;
    }
}
