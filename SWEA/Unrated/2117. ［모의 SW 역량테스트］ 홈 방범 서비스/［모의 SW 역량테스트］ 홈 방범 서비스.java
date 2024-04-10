import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    static int TestCaseNum, BoardSize, HomeCost, HomeCnt;
    static boolean[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int maxCareNum, totalMaxCareNum;

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        TestCaseNum = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= TestCaseNum; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            BoardSize = Integer.parseInt(st.nextToken());
            HomeCost = Integer.parseInt(st.nextToken());
            board = new boolean[BoardSize][BoardSize];
            maxCareNum = 0;
            HomeCnt = 0;
            for (int row = 0; row < BoardSize; row++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int col = 0; col < BoardSize; col++) {
                    if (st.nextToken().equals("1")) {
                        board[row][col] = true;
                        HomeCnt++;
                    }
                }
            }
            simulation();
            answer.append("#").append(tc).append(" ").append(totalMaxCareNum).append("\n");
        }
        System.out.println(answer);
    }

    private static void simulation() {
        totalMaxCareNum = 0;
        for (int i = 0; i < BoardSize; i++) {
            for (int j = 0; j < BoardSize; j++) {
                startService(i, j);
                totalMaxCareNum = Math.max(totalMaxCareNum, maxCareNum);
            }
        }
    }

    private static void startService(int x, int y) {
        boolean[][] serviceBoard = new boolean[BoardSize][BoardSize];
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(x, y));
        serviceBoard[x][y] = true;

        // 시작 지점에 집이 있는 경우
        if (board[x][y]) {
            maxCareNum = 1;
        } else {
            maxCareNum = 0;
        }

        int depth = 1;
        while (!q.isEmpty()) {
            int qSize = q.size();
            depth++;
            for (int k = 0; k < qSize; k++) {
                Point curPoint = q.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = curPoint.x + dx[i];
                    int ny = curPoint.y + dy[i];

                    if (nx < 0 || nx >= BoardSize || ny < 0 || ny >= BoardSize) {
                        continue;
                    }

                    if (serviceBoard[nx][ny]) {
                        continue;
                    }

                    serviceBoard[nx][ny] = true;
                    q.add(new Point(nx, ny));
                }
            }

            int cost = depth * depth + (depth - 1) * (depth - 1);
            if (cost > HomeCnt * HomeCost) {
                return;
            }
            int curCareCnt = getCareHomeCnt(serviceBoard);
            int profit = curCareCnt * HomeCost;

            if (profit-cost >= 0 && curCareCnt > maxCareNum) {
                maxCareNum = curCareCnt;
            }

        }
    }

    private static int getCareHomeCnt(boolean[][] serviceBoard) {
        int hCnt = 0;
        for (int i = 0; i < BoardSize; i++) {
            for (int j = 0; j < BoardSize; j++) {
                if (board[i][j] && serviceBoard[i][j]) {
                    hCnt++;
                }
            }
        }
        return hCnt;
    }
}
