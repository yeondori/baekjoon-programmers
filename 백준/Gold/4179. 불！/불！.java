import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    final static char FIRE='F', WALL='#', START='J', VACANT = '.';
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static boolean endFire, endJihoon, exit;

    static char[][] board;
    static boolean[][] visitFire, visitJihoon;
    static int ROW, COL, exitTime;

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static Queue<Point> fires, jihoon;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ROW = Integer.parseInt(st.nextToken());
        COL = Integer.parseInt(st.nextToken());

        board = new char[ROW][COL];
        fires = new ArrayDeque<>();
        jihoon = new ArrayDeque<>();
        visitFire = new boolean[ROW][COL];
        visitJihoon = new boolean[ROW][COL];
        for (int i = 0; i < ROW; i++) {
            String input = br.readLine();
            for (int j = 0; j < COL; j++) {
                board[i][j] = input.charAt(j);
                if (board[i][j] == FIRE) {
                    fires.add(new Point(i, j));
                    visitFire[i][j] = true;
                } else if (board[i][j] == START) {
                    jihoon.add(new Point(i, j));
                    visitJihoon[i][j] = true;
                    // 바로 탈출 가능한 경우
                    if (canExit(i, j)) {
                        exit = true;
                    }
                }
            }
        }

        // 바로 탈출 가능한 경우
        if (exit) {
            System.out.println(1);
            return;
        }
        simulation();
    }

    private static void simulation() {
        int time = 1;
        while (true) {
            // 불 확산 & 지훈이 이동
            bfs();
            time++;

            if (exit) {
                exitTime = time;
                break;
            }

            if (endFire && endJihoon) {
                exitTime = Integer.MAX_VALUE;
                break;
            }
        }
        System.out.println(exitTime==Integer.MAX_VALUE?"IMPOSSIBLE" : exitTime);
    }

    private static void bfs() {
        // 1. 불 확산
        int fireSize = fires.size();
        if (fireSize == 0) {
            endFire = true;
        }
        for (int i = 0; i < fireSize; i++) {
            Point curFire = fires.poll();

            for (int j = 0; j < 4; j++) {
                int nx = curFire.x + dx[j];
                int ny = curFire.y + dy[j];

                if (outOfRange(nx, ny) || visitFire[nx][ny] || board[nx][ny] == WALL) {
                    continue;
                }

                visitFire[nx][ny] = true;
                board[nx][ny] = FIRE;
                fires.add(new Point(nx, ny));
            }
        }

        // 2. 지훈이 이동
        int jihoonSize = jihoon.size();
        if (jihoonSize == 0) {
            endJihoon = true;
        }
        for (int i = 0; i < jihoonSize; i++) {
            Point curJihoon = jihoon.poll();

            for (int j = 0; j < 4; j++) {
                int nx = curJihoon.x + dx[j];
                int ny = curJihoon.y + dy[j];

                if (outOfRange(nx, ny) || visitJihoon[nx][ny] || board[nx][ny] == FIRE || board[nx][ny] == WALL) {
                    continue;
                }

                if (canExit(nx, ny)) {
                    exit = true;
                    return;
                }
                visitJihoon[nx][ny] = true;
                jihoon.add(new Point(nx, ny));
            }
        }

    }

    private static boolean canExit(int x, int y) {
        return x == 0 || x == ROW - 1 || y == 0 || y == COL - 1;
    }

    private static boolean outOfRange(int x, int y) {
        return x < 0 || x >= ROW || y < 0 || y >= COL;
    }
}
