import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    static int T, N;
    static boolean[][] board;
    static List<int[]> cores;
    static int coreSize, maxCoreCount, minWire;
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
            cores = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = st.nextToken().equals("1");
                    if (board[i][j]) {
                        // 가장자리에 있는 코어는 제외하고 카운트
                        if (i == 0 || j == 0 || i == N - 1 || j == N - 1) {
                            continue;
                        }
                        cores.add(new int[]{i, j});
                    }
                }
            }
            getAnswer();
            printer.append(minWire).append("\n");
        }
        System.out.println(printer);
    }

    private static void getAnswer() {
        // 초기화
        coreSize = cores.size();
        maxCoreCount = 0;
        minWire = Integer.MAX_VALUE;
        // dfs
        dfs(0, 0, 0);
    }

    private static void dfs(int wire, int coreIndex, int coreCount) {
        // 모든 코어를 탐색한 경우
        if (coreIndex == coreSize) {
            // 선택된 코어 개수가 갱신되지 않은 경우, 전선 길이의 합을 최소로 갱신
            if (maxCoreCount == coreCount) {
                minWire = Math.min(minWire, wire);
            } else if (maxCoreCount < coreCount) { // 선택된 코어 개수가 기존값보다 큰 경우, 코어 개수와 전선 합 갱신
                maxCoreCount = coreCount;
                minWire = wire;
            }
            return;
        }

        int x = cores.get(coreIndex)[0];
        int y = cores.get(coreIndex)[1];

        // 해당 코어를 선택하지 않았을 때
        dfs(wire, coreIndex + 1, coreCount);
        // 해당 코어를 선택할 때, 4방향을 모두 고려
        for (int i = 0; i < 4; i++) {
            boolean isInterrupted = false; // while문을 수행 완료했는지를 구별하기 위한 flag
            int nx = x + dx[i];
            int ny = y + dy[i];

            int cnt = 0; // 해당 방향으로 전선을 설치하는 경우, 전선의 길이
            while (nx >= 0 && nx < N && ny >= 0 && ny < N) { // Board의 가장자리에 도착하면 while문 종료
                if (board[nx][ny]) {    // 코어나 전선이 존재하는 경우 해당 방향으로 전선을 설치할 수 없다.
                    isInterrupted = true;
                    break;
                }

                nx += dx[i];
                ny += dy[i];
                ++cnt;
            }

            if (isInterrupted) {    // while 문을 정상 수행 완료하지 못한 경우 다른 방향을 탐색
                continue;
            }

            setBoard(coreIndex, i, true); // 전선 설치
            dfs(wire + cnt, coreIndex + 1, coreCount + 1); // 전선 길이 합과 코어 인덱스, 선택한 코어 개수 갱신 후, 다음 코어 탐색
            setBoard(coreIndex, i, false);    // 전선 원복

        }

    }

    private static void setBoard(int cellIdx, int dir, boolean setValue) {
        int[] cur = cores.get(cellIdx); // 전선 설치할 코어 위치
        
        if (dir < 2) {     // 상, 하
            for (int i = cur[0] + dx[dir]; i >= 0 && i < N; i += dx[dir]) {
                board[i][cur[1]] = setValue;
            }
            return;
        }
        for (int i = cur[1] + dy[dir]; i>=0 && i < N; i+=dy[dir]) {     // 좌, 우
            board[cur[0]][i] = setValue;
        }
    }
}
