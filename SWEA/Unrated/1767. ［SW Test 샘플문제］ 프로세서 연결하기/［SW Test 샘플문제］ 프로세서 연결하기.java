import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    static int T, N;
    static boolean[][] board;
    static int answer;
    static List<int[]> cells;
    static int cellSize, maxCellCount, minWire;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder printer = new StringBuilder();

        T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            printer.append("#").append(tc).append(" ");
            N = Integer.parseInt(br.readLine().trim());
            board = new boolean[N][N];
            cells = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = st.nextToken().equals("1");
                    if (board[i][j]) {
                        if (i == 0 || j == 0 || i == N - 1 || j == N - 1) {
                            continue;
                        }
                        cells.add(new int[]{i, j});
                    }
                }
            }
            cellSize = cells.size();
            getAnswer();
            printer.append(minWire).append("\n");
        }
        System.out.println(printer);
    }

    private static void getAnswer() {
        maxCellCount = 0;
        minWire = Integer.MAX_VALUE;
        dfs(0, 0, 0);
    }

    private static void dfs(int wire, int cellIdx, int cellCount) {
        if (cellIdx == cellSize) {
            if (maxCellCount == cellCount) {
                minWire = Math.min(minWire, wire);
            } else if (maxCellCount < cellCount) {
                maxCellCount = cellCount;
                minWire = wire;
            }
            return;
        }

        int x = cells.get(cellIdx)[0];
        int y = cells.get(cellIdx)[1];

        // dfs() 내가 해당 코어를 선택하지 않았을 때
        dfs(wire, cellIdx + 1, cellCount);
        // 코어를 선택할 때
        for (int i = 0; i < 4; i++) {
            boolean isInterrupted = false;

            int nx = x + dx[i];
            int ny = y + dy[i];

            int cnt = 0;
            while (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                if (board[nx][ny]) {
                    isInterrupted = true;
                    break;
                }

                nx += dx[i];
                ny += dy[i];
                ++cnt;
            }

            if (isInterrupted) {
                continue;
            }

            setBoard(cellIdx, i, true); // 전선 설정
            dfs(wire + cnt, cellIdx + 1, cellCount + 1);
            setBoard(cellIdx, i, false);    // 원복

        }

    }

    private static void setBoard(int cellIdx, int dir, boolean setValue) {
        int[] cur = cells.get(cellIdx);
        if (dir == 0) {     // 상
            for (int i = cur[0] - 1; i >= 0; i--) {
                board[i][cur[1]] = setValue;
            }
            return;
        }
        if (dir == 1) {     // 하
            for (int i = cur[0] + 1; i < N; i++) {
                board[i][cur[1]] = setValue;
            }
            return;
        }
        if (dir == 2) {     // 좌
            for (int i = cur[1] - 1; i >= 0; i--) {
                board[cur[0]][i] = setValue;
            }
            return;
        }
        for (int i = cur[1] + 1; i < N; i++) {     // 우
            board[cur[0]][i] = setValue;
        }
    }
}
