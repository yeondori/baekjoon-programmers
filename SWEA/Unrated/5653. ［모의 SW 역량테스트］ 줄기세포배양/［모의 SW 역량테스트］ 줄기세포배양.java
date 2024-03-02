import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static int T, N, M, K;

    static class Cell {
        int x, y, t, acT, deacT;

        public Cell(int x, int y, int t, int acT, int deacT) {
            this.x = x;
            this.y = y;
            this.t = t;
            this.acT = acT;
            this.deacT = deacT;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Cell{");
            sb.append("x=").append(x);
            sb.append(", y=").append(y);
            sb.append(", t=").append(t);
            sb.append(", acT=").append(acT);
            sb.append(", deacT=").append(deacT);
            sb.append('}');
            return sb.toString();
        }
    }

    static int[][] board;
    final static int ADJ_NUM = 200;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static List<Cell> cells, nextCell;

    public static void main(String[] args) throws IOException {
        StringBuilder answer = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            cells = new ArrayList<>();
            board = new int[401][401];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int cur = Integer.parseInt(st.nextToken());
                    if (cur != 0) {
                        cells.add(new Cell(i + ADJ_NUM, j + ADJ_NUM, cur, cur, cur * 2));
                        board[i + ADJ_NUM][j + ADJ_NUM] = 1;
                    }
                }
            }
            simulation();
            answer.append("#").append(tc).append(" ").append(cells.size()).append("\n");
        }
        System.out.println(answer);
    }

    private static void simulation() {
        for (int curTime = 1; curTime <= K; curTime++) {
            int cellSize = cells.size();
            nextCell = new ArrayList<>();
            for (int i = cellSize - 1; i >= 0; i--) {
                Cell curCell = cells.get(i);
                if (curTime == curCell.deacT) {
                    board[curCell.x][curCell.y] = -1;
                }
                if (curTime > curCell.acT) {
                    for (int dir = 0; dir < 4; dir++) {
                        spreadCell(curCell, curTime, dir);
                    }
                }
            }
            cellSize = cells.size();
            for (int i = cellSize - 1; i >= 0; i--) {
                Cell c = cells.get(i);
                if (board[c.x][c.y] > 1) {
                    board[c.x][c.y] = 1;
                } else if (board[c.x][c.y] == -1) {
                    cells.remove(i);
                }
            }
        }
    }

    private static void spreadCell(Cell cell, int curTime, int dir) {
        int nx = cell.x + dx[dir];
        int ny = cell.y + dy[dir];

        if (board[nx][ny] == 0) {
            cells.add(new Cell(nx, ny, cell.t, curTime + cell.t, curTime + 2 * cell.t));
            int curIdx = cells.size() - 1;
            board[nx][ny] = curIdx + 2;
        } else if (board[nx][ny] > 1) {
            Cell prevCell = cells.get(board[nx][ny] - 2);
            if (prevCell.t < cell.t) {
                prevCell.t = cell.t;
                prevCell.acT = curTime + cell.t;
                prevCell.deacT = curTime + 2 * cell.t;
            }
        }
    }
}
