import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int Size, VirusNum, TargetRow, TargetCol, MaxTime;
    static int blankNum;

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static List<Point>[] virus;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Size = Integer.parseInt(st.nextToken());
        VirusNum = Integer.parseInt(st.nextToken());
        board = new int[Size][Size];
        blankNum = Size * Size;
        
        virus = new ArrayList[VirusNum + 1];
        for (int i = 1; i <= VirusNum; i++) {
            virus[i] = new ArrayList<>();
        }

        for (int i = 0; i < Size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < Size; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] > 0) {
                    virus[board[i][j]].add(new Point(i, j));
                    blankNum--;
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        MaxTime = Integer.parseInt(st.nextToken());
        TargetRow = Integer.parseInt(st.nextToken());
        TargetCol = Integer.parseInt(st.nextToken());

        simulation();
    }

    private static void simulation() {
        int time = 0;
        while (time < MaxTime) {

            for (int i = 1; i <= VirusNum; i++) {
                spread(i);

                if (blankNum == 0) {
                    break;
                }
            }
            time++;
        }

        System.out.println(board[TargetRow-1][TargetCol-1]);
    }

    private static void spread(int vNum) {
        List<Point> newVirus = new ArrayList<>();

        for (Point cur : virus[vNum]) {
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (inRange(nx, ny) && board[nx][ny] == 0) {
                    board[nx][ny] = vNum;
                    blankNum--;
                    newVirus.add(new Point(nx, ny));
                }
            }
        }
        virus[vNum].addAll(newVirus);
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < Size && y >= 0 && y < Size;
    }
}
