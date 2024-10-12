import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static final int ROW = 8, COL = 7, DOMINO_NUM = 7;

    static int answer;

    static int[][] board = new int[ROW][COL];
    static boolean[][] visit = new boolean[ROW][COL], domino = new boolean[DOMINO_NUM][DOMINO_NUM];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < ROW; i++) {
            String input = br.readLine();
            for (int j = 0; j < COL; j++) {
                board[i][j] = input.charAt(j) - '0';
            }
        }

        solve(0, 0);
        System.out.println(answer);
    }

    static void solve(int x, int y) {
        if (x == ROW) {
            answer++;
            return;
        }

        if (y == COL) {
            solve(x+1, 0);
            return;
        }

        if (visit[x][y]) {
            solve(x, y + 1);
            return;
        }

        if (inRange(x, y+1) && !visit[x][y+1]  && canPlaceDomino(board[x][y], board[x][y+1])) {
            domino[board[x][y]][board[x][y+1]] = true;
            domino[board[x][y+1]][board[x][y]] = true;

            visit[x][y] = true;
            visit[x][y+1] = true;

            solve(x, y + 2);

            domino[board[x][y]][board[x][y+1]] = false;
            domino[board[x][y+1]][board[x][y]] = false;

            visit[x][y] = false;
            visit[x][y+1] = false;
        }

        if (inRange(x + 1, y) && !visit[x+1][y] && canPlaceDomino(board[x][y], board[x+1][y])) {
            domino[board[x+1][y]][board[x][y]] = true;
            domino[board[x][y]][board[x+1][y]] = true;

            visit[x][y] = true;
            visit[x+1][y] = true;

            solve(x, y + 1);

            domino[board[x+1][y]][board[x][y]] = false;
            domino[board[x][y]][board[x+1][y]] = false;

            visit[x][y] = false;
            visit[x+1][y] = false;
        }
    }

    static boolean canPlaceDomino(int val1, int val2) {
        return !domino[val1][val2];
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && x < ROW && y >= 0 && y < COL;
    }
}
