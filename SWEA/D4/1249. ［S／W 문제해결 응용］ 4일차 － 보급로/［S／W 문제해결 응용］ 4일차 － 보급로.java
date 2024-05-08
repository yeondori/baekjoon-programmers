import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    final static int INF = Integer.MAX_VALUE>>2;
    static int TestCase, BoardSize;
    static int[][] board, cost;

    static class Point implements Comparable<Point> {
        int x;
        int y;
        int cost;

        public Point(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Point o) {
            return this.cost-o.cost;
        }
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        TestCase = Integer.parseInt(br.readLine().trim());
        for (int tc=1; tc<=TestCase; tc++) {
            BoardSize = Integer.parseInt(br.readLine().trim());
            board = new int[BoardSize][BoardSize];
            for (int row = 0; row < BoardSize; row++) {
                String[] input = br.readLine().split("");
                for (int col = 0; col < BoardSize; col++) {
                    board[row][col] = Integer.parseInt(input[col]);
                }
            }
            getMinRepairTime();
            answer.append("#").append(tc).append(" ").append(cost[BoardSize-1][BoardSize-1]).append("\n");
        }
        System.out.println(answer);
    }

    private static void getMinRepairTime() {
        cost = new int[BoardSize][BoardSize];
        for (int row = 0; row < BoardSize; row++) {
            Arrays.fill(cost[row], INF);
        }
        cost[0][0] = board[0][0];
        PriorityQueue<Point> q = new PriorityQueue<>();
        q.add(new Point(0, 0, board[0][0]));
        while (!q.isEmpty()) {
            Point curPoint = q.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nx = curPoint.x + dx[dir];
                int ny = curPoint.y + dy[dir];

                if (nx < 0 || nx >= BoardSize || ny < 0 || ny >= BoardSize) {
                    continue;
                }

                int newCost = curPoint.cost + board[nx][ny];
                if (cost[nx][ny] > newCost) {
                    cost[nx][ny] = newCost;
                    q.add(new Point(nx, ny, newCost));
                }
            }
        }
    }
}
