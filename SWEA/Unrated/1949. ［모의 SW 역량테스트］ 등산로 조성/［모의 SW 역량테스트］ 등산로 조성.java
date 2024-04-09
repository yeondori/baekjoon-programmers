import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int TestCaseNum, BoardSize, MaxRemove;
    static int[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int startNumber, maxLength;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        TestCaseNum = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= TestCaseNum; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            BoardSize = Integer.parseInt(st.nextToken());
            MaxRemove = Integer.parseInt(st.nextToken());

            board = new int[BoardSize][BoardSize];
            visited = new boolean[BoardSize][BoardSize];
            startNumber = 0;
            for (int row = 0; row < BoardSize; row++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int col = 0; col < BoardSize; col++) {
                    board[row][col] = Integer.parseInt(st.nextToken());
                    startNumber = Math.max(startNumber, board[row][col]);
                }
            }

            getAnswer();
            answer.append("#").append(tc).append(" ").append(maxLength).append("\n");
        }
        System.out.println(answer);
    }

    private static void getAnswer() {
        maxLength = 0;
        // startNumber에서만 시작할 수 있다.
        for (int row = 0; row < BoardSize; row++) {
            for (int col = 0; col < BoardSize; col++) {
                if (board[row][col] == startNumber) {
                    visited[row][col] = true; // 시작 위치 방문 표시
                    getMaxLength(row, col, false, 1);
                    visited[row][col] = false; // 시작 위치 방문 해제
                }
            }
        }
    }

    private static void getMaxLength(int x, int y, boolean useRemove, int cnt) {
        // 현재 위치에서의 최대 등산로 길이 업데이트
        maxLength = Math.max(maxLength, cnt);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (outOfRange(nx, ny) || visited[nx][ny]) {
                continue;
            }

            // 다음 위치가 더 낮은 곳일 때 이동
            if (board[nx][ny] < board[x][y]) {
                visited[nx][ny] = true;
                getMaxLength(nx, ny, useRemove, cnt + 1);
                visited[nx][ny] = false; // 이동 후 방문 해제
            } else if (!useRemove && board[nx][ny] - board[x][y] < MaxRemove) {
                // 공사해서 이동 가능한 경우
                int diff = board[nx][ny] - board[x][y];
                int origin = board[nx][ny];
                board[nx][ny] -= diff + 1; // 현재 위치보다 1만큼 낮게 설정하여 공사
                visited[nx][ny] = true;
                getMaxLength(nx, ny, true, cnt + 1);
                board[nx][ny] = origin; // 원래 높이로 복원
                visited[nx][ny] = false; // 공사 후 방문 해제
            }
        }
    }

    private static boolean outOfRange(int x, int y) {
        return x < 0 || x >= BoardSize || y < 0 || y >= BoardSize;
    }
}
