import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {

    static int size, moveNum;
    static int[][] board;
    static class Cloud {
        int x, y;

        Cloud(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Cloud)) {
                return false;
            }

            Cloud cloud = (Cloud) obj;
            return x == cloud.x && y == cloud.y;
        }
    }
    static List<Cloud> clouds;

    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        size = Integer.parseInt(st.nextToken());
        moveNum = Integer.parseInt(st.nextToken());

        board = new int[size][size];
        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int dir, dist;
        init();
        for (int i = 0; i < moveNum; i++) {
            st = new StringTokenizer(br.readLine());

            dir = Integer.parseInt(st.nextToken()) - 1;
            dist = Integer.parseInt(st.nextToken());

            move(dir, dist);
            rain();
            copyWater();
            makeCloud();
        }

        System.out.println(getTotalWater());
    }

    private static void init() {
        clouds = new ArrayList<>();

        clouds.add(new Cloud(size-1, 0));
        clouds.add(new Cloud(size-1, 1));
        clouds.add(new Cloud(size-2, 0));
        clouds.add(new Cloud(size-2, 1));
    }

    private static void move(int dir, int dist) {
        for (Cloud cloud : clouds) {

            dist = dist %  size;

            cloud.x = (cloud.x + dx[dir] * dist + size) % size;
            cloud.y = (cloud.y + dy[dir] * dist + size) % size;
        }
    }

    private static void rain() {
        for (Cloud cloud : clouds) {
            board[cloud.x][cloud.y] += 1;
        }
    }

    private static void copyWater() {
        int cnt, nx, ny;

        for (Cloud cloud : clouds) {
            cnt = 0;
            for (int i = 1; i < 8; i+=2) {
                nx = cloud.x + dx[i];
                ny = cloud.y + dy[i];

                if (valid(nx, ny)) cnt++;
            }

            board[cloud.x][cloud.y] += cnt;
        }
    }

    private static boolean valid(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size && board[x][y] > 0;
    }

    private static void makeCloud() {
        List<Cloud> nextClouds = new ArrayList<>();
        Cloud curCloud;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                curCloud = new Cloud(i, j);
                if (board[i][j] >= 2 && !clouds.contains(curCloud)) {
                    board[i][j] -= 2;
                    nextClouds.add(curCloud);
                }
            }
        }

        clouds = nextClouds;
    }

    private static int getTotalWater() {
        int total = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                total += board[i][j];
            }
        }

        return total;
    }
}
