import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, H, remain, time;
    static Queue<int[]> q;

    static int[] dx = {-1, 1, 0, 0, 0, 0};
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1};

    static int[][][] tomatoes;
    static boolean[][][] visited;
    static final int UNRIPE = 0, RIPEN = 1;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        tomatoes = new int[M][N][H];
        visited = new boolean[M][N][H];
        q = new ArrayDeque<>();
        int x, z;
        for (int i = 0; i < M*H; i++) {
            st = new StringTokenizer(br.readLine());

            z = i /M;
            x = i % M;
            for (int y = 0; y < N; y++) {
                int curTomato = Integer.parseInt(st.nextToken());
                tomatoes[x][y][z] = curTomato;

                if (curTomato == UNRIPE) {
                    remain++;
                } else if (curTomato == RIPEN) {
                    q.add(new int[]{x, y, z});
                    visited[x][y][z] = true;
                }
            }
        }

        bfs();
        System.out.println(remain==0? time:-1);
    }

    private static void bfs() {
        int[] cur;
        int size, nx, ny, nz;
        while (!q.isEmpty()) {
            if (remain == 0) {
                return;
            }

            size = q.size();
            for (int i = 0; i < size; i++) {
                cur = q.poll();

                for (int k = 0; k < 6; k++) {
                    nx = cur[0] + dx[k];
                    ny = cur[1] + dy[k];
                    nz = cur[2] + dz[k];

                    if (outOfRange(nx, ny, nz) || visited[nx][ny][nz]) {
                        continue;
                    }

                    visited[nx][ny][nz] = true;

                    if (tomatoes[nx][ny][nz] == UNRIPE) {
                        remain--;
                        tomatoes[nx][ny][nz] = RIPEN;
                        q.add(new int[]{nx, ny, nz});
                    }
                }
            }
            time++;
        }
    }

    private static boolean outOfRange(int x, int y, int z) {
        return x < 0 || x >= M || y < 0 || y >= N || z < 0 || z >= H;
    }
}
