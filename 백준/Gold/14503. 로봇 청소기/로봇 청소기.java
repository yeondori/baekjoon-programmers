import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int row, col, x, y, dir;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int[][] board;
    static final int DIRTY = 0, WALL = 1, CLEANED = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());

        board = new int[row][col];
        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clean();
    }


    static void clean() {
        int cleanArea = 0;

        int nx, ny;
        while(true) {

            // 1. 현재 칸 청소
            if (board[x][y] == DIRTY) {
                cleanArea++;
                board[x][y] = CLEANED;
            }

            // 2. 인접 4방향 청소 가능 여부 확인
            boolean canClean = false;
            for (int d = 0; d < 4; d++) {
                dir = (dir + 3) % 4;

                nx = x + dx[dir];
                ny = y + dy[dir];

                if (inRange(nx, ny) && board[nx][ny] == DIRTY) {
                    canClean = true;

                    x = nx;
                    y = ny;

                    break;
                }
            }

            if (!canClean) {
                dir = (dir + 2) % 4; // 후진할 방향

                nx = x + dx[dir];
                ny = y + dy[dir];

                if (inRange(nx, ny) && board[nx][ny]!= WALL) {
                    x = nx;
                    y = ny;
                    dir = (dir + 2) % 4; // 원복
                } else {
                    break;
                }
            }
        }

        System.out.println(cleanArea);
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && x < row && y >= 0 && y < col;
    }
}
