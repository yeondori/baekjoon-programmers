import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int T, N, W, H, remainBricks;
    static int[][] board, simulationBoard;
    static int[] ballNum;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            board = new int[H][W];
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            remainBricks = Integer.MAX_VALUE;
            crashBricks();
            answer.append("#").append(tc).append(" ").append(remainBricks).append("\n");
        }
        System.out.println(answer);
    }

    private static void crashBricks() {
        ballNum = new int[N];
        permutation(0);
    }

    private static void permutation(int idx) {
        if (idx == N) {
            play();
            return;
        }

        for (int i = 0; i < W; i++) {
            ballNum[idx] = i;
            permutation(idx + 1);
        }
    }

    private static void play() {
        simulationBoard = new int[H][W];
        for (int i = 0; i < H; i++) {
            simulationBoard[i] = board[i].clone();
        }

        for (int i = 0; i < N; i++) {
            dropBalls(ballNum[i]);
        }
        int cnt = countBricks();
        if (cnt < remainBricks) {
            remainBricks = cnt;
        }
    }

    private static void dropBalls(int col) {
        for (int row = 0; row < H; row++) {
            if (simulationBoard[row][col] != 0) {
                crashBricks(row, col);
                fallBricks();
                break;
            }
        }
    }

    private static void crashBricks(int x, int y) {
        int cnt = simulationBoard[x][y] - 1;
        simulationBoard[x][y] = 0;
        if (cnt == 0) {
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nx = x;
            int ny = y;
            for (int i = 0; i < cnt; i++) {
                nx += dx[d];
                ny += dy[d];

                if (nx < 0 || nx >= H || ny < 0 || ny >= W) {
                    break;
                }

                if (simulationBoard[nx][ny] != 0) {
                    crashBricks(nx, ny);
                }
            }
        }
    }

    private static void fallBricks() {
        for (int col = 0; col < W; col++) {
            for (int row = H - 1; row >= 0; row--) {
                if (simulationBoard[row][col] == 0) {
                    int emptyRow = row;
                    for (int r = row - 1; r >= 0; r--) {
                        if (simulationBoard[r][col] != 0) {
                            simulationBoard[emptyRow][col] = simulationBoard[r][col];
                            simulationBoard[r][col] = 0;
                            emptyRow--;
                        }
                    }
                }
            }
        }
    }

    private static int countBricks() {
        int cnt = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (simulationBoard[i][j] != 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}