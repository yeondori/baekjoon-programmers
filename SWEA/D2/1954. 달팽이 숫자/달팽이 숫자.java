import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int T, N, cnt;
    static int[][] snail;
    static int direction;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            snail = new int[N][N];
            cnt = 0;
            System.out.println("#" + tc);
            makeSnail(0, 0, direction);
        }
    }

    private static void makeSnail(int x, int y, int direction) {
        if (cnt == N * N) {
            printSnail();
            return;
        }
        snail[x][y] = ++cnt;

        int nx = x + dx[direction];
        int ny = y + dy[direction];
        if (nx < 0 || nx >= N || ny < 0 || ny >= N || snail[nx][ny] != 0) {
            direction++;
            if (direction == 4) {
                direction = 0;
            }
            makeSnail(x + dx[direction], y + dy[direction], direction);
            return;
        }
        makeSnail(nx, ny, direction);
    }

    private static void printSnail() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(snail[i][j] + " ");
            }
            System.out.println();
        }
    }
}
