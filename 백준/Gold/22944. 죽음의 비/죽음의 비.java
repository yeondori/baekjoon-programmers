import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int boardSize, Hp, Umbrella, umbrellaCnt;
    static int strX, strY, endX, endY;
    static char[][] board;

    static class Point {
        int x, y, hp, umbrella, cnt;

        public Point(int x, int y, int hp, int umbrella, int cnt) {
            this.x = x;
            this.y = y;
            this.hp = hp;
            this.umbrella = umbrella;
            this.cnt = cnt;
        }
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        boardSize = Integer.parseInt(st.nextToken());
        Hp = Integer.parseInt(st.nextToken());
        Umbrella = Integer.parseInt(st.nextToken());

        board = new char[boardSize][boardSize];

        for (int row = 0; row < boardSize; row++) {
            String line = br.readLine();
            for (int col = 0; col < boardSize; col++) {
                board[row][col] = line.charAt(col);
                if (board[row][col] == 'S') {
                    strX = row;
                    strY = col;
                } else if (board[row][col] == 'E') {
                    endX = row;
                    endY = col;
                } else if (board[row][col] == 'U') {
                    umbrellaCnt++;
                }
            }
        }

        move();
    }

    private static void move() {
        Queue<Point> q = new ArrayDeque<>();
        int[][] visited = new int[boardSize][boardSize];
        q.offer(new Point(strX, strY, Hp, 0, 0));
        visited[strX][strY] = Hp;

        int minMove = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextUmbrella = cur.umbrella;
                int nextHp = cur.hp;
                int nextCnt = cur.cnt;

                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx >= boardSize || ny < 0 || ny >= boardSize) {
                    continue;
                }

                if (nx == endX && ny == endY) {
                    minMove = Math.min(minMove, cur.cnt + 1);
                    continue;
                }

                if (board[nx][ny] == 'U') {
                    nextUmbrella = Umbrella;
                }

                if (nextUmbrella != 0) {
                    nextUmbrella--;
                } else {
                    nextHp--;
                }

                if (nextHp == 0) {
                    continue;
                }

                if (visited[nx][ny] < nextHp + nextUmbrella) {
                    visited[nx][ny] = nextHp + nextUmbrella;
                    q.add(new Point(nx, ny, nextHp, nextUmbrella, nextCnt + 1));
                }
            }
        }
        System.out.println(minMove == Integer.MAX_VALUE ? -1 : minMove);
    }
}
