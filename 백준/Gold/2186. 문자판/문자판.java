import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int Row, Col, MaxMove, length;
    static char[][] board;
    static String target;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Row = Integer.parseInt(st.nextToken());
        Col = Integer.parseInt(st.nextToken());
        MaxMove = Integer.parseInt(st.nextToken());

        board = new char[Row][Col];
        for (int i = 0; i < Row; i++) {
            String input = br.readLine();
            for (int j = 0; j < Col; j++) {
                board[i][j] = input.charAt(j);
            }
        }

        target = br.readLine().trim();
        length = target.length();

        dp = new int[Row][Col][length];
        for (int i = 0; i < Row; i++) {
            for (int j = 0; j < Col; j++) {
                for (int k = 0; k < length; k++) {
                    dp[i][j][k] = -1; 
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < Row; i++) {
            for (int j = 0; j < Col; j++) {
                if (board[i][j] == target.charAt(0)) {
                    answer += find(i, j, 0);
                }
            }
        }
        System.out.println(answer);
    }

    private static int find(int x, int y, int cnt) {
        if (cnt == length - 1) {
            return 1;
        }

        if (dp[x][y][cnt] != -1) {
            return dp[x][y][cnt];
        }

        int count = 0;
        for (int k = 1; k <= MaxMove; k++) {
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i] * k;
                int ny = y + dy[i] * k;

                if (outOfRange(nx, ny) || board[nx][ny] != target.charAt(cnt + 1)) {
                    continue;
                }

                count += find(nx, ny, cnt + 1);
            }
        }

        dp[x][y][cnt] = count;
        return count;
    }

    private static boolean outOfRange(int x, int y) {
        return x < 0 || x >= Row || y < 0 || y >= Col;
    }
}
