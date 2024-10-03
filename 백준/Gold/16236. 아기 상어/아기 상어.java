import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int BABY_SHARK = 9;
    static int oceanSize, sharkX, sharkY, eatNum;
    static int sharkSize = 2;

    static class Fish implements Comparable<Fish> {
        int x, y, dist;

        public Fish(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Fish o) {
            int diffDist = this.dist - o.dist;
            int diffX = this.x - o.x;
            int diffY = this.y - o.y;

            if (diffDist == 0) {
                if (diffX == 0) {
                    return diffY;
                }
                return diffX;
            }
            return diffDist;
        }
    }

    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    static int[][] ocean;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        oceanSize = Integer.parseInt(br.readLine());

        ocean = new int[oceanSize][oceanSize];
        StringTokenizer st;
        for (int i = 0; i < oceanSize; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < oceanSize; j++) {
                ocean[i][j] = Integer.parseInt(st.nextToken());

                if (ocean[i][j] == BABY_SHARK) {
                    sharkX = i;
                    sharkY = j;
                    ocean[i][j] = 0;
                }
            }
        }

        solve();
    }

    static void solve() {
        int time = 0;

        while (true) {

            Fish curFish = findFood();
            if (curFish == null) break;

            time += curFish.dist;
            sharkX = curFish.x;
            sharkY = curFish.y;
            ocean[sharkX][sharkY] = 0;
            eatNum++;

            if (eatNum == sharkSize) {
                sharkSize++;
                eatNum = 0;
            }
        }

        System.out.println(time);
    }

    static Fish findFood() {
        boolean[][] visited = new boolean[oceanSize][oceanSize];
        Queue<Fish> q = new ArrayDeque<>();
        PriorityQueue<Fish> foods = new PriorityQueue<>();
        q.add(new Fish(sharkX, sharkY, 0));

        while (!q.isEmpty()) {
            Fish cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (inRange(nx, ny) && !visited[nx][ny] && ocean[nx][ny] <= sharkSize) {
                    visited[nx][ny] = true;
                    if (ocean[nx][ny] > 0 && ocean[nx][ny] < sharkSize) {
                        foods.add(new Fish(nx, ny, cur.dist + 1));
                    }
                    q.add(new Fish(nx, ny, cur.dist + 1));
                }
            }
        }

        if (foods.isEmpty()) {
            return null;
        }
        return foods.poll();
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && x < oceanSize && y >= 0 && y < oceanSize;
    }
}
