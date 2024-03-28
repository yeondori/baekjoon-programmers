import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] board;
    static int[][][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Point {
        int x;
        int y;
        boolean isWater;

        public Point(int x, int y, boolean isWater) {
            this.x = x;
            this.y = y;
            this.isWater = isWater;
        }
    }

    static Point str;
    static List<Point> waters;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        visited = new int[R][C][2];
        waters = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = input.charAt(j);

                if (board[i][j] == 'S') {
                    str = new Point(i, j, false);
                } else if (board[i][j] == '*') {
                    waters.add(new Point(i, j, true));
                }

            }
        }
        int minTime = bfs();
        System.out.println(minTime == -1 ? "KAKTUS" : minTime);
    }

    private static int bfs() {
        Queue<Point> q = new LinkedList<>();
        for (Point water : waters) {    // 물 채우기
            q.add(water);
            visited[water.x][water.y][1] = 1;
        }
        q.add(str);
        visited[str.x][str.y][0] = 1;

        while (!q.isEmpty()) {
            Point cur = q.poll();
            int sep = cur.isWater ? 1 : 0;
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx == R || ny < 0 || ny == C) {
                    continue;
                }

                if (!cur.isWater && board[nx][ny] == 'D') {
                    return visited[cur.x][cur.y][sep];
                }

                if (visited[nx][ny][sep] != 0 || board[nx][ny] == 'X' || board[nx][ny] == 'D') {
                    continue;
                }

                if (cur.isWater) {
                    board[nx][ny] = '*';
                    visited[nx][ny][1] = 1;
                    q.add(new Point(nx, ny, true));
                } else {
                    if (board[nx][ny] == '*') {
                        continue;
                    }
                    visited[nx][ny][sep] = visited[cur.x][cur.y][sep] + 1;
                    q.add(new Point(nx, ny, false));
                }
            }
        }
        return -1;
    }
}
