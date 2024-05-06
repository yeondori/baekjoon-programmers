import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static final int PLAYER_NUM = 9, MAX_OUT = 3;
    static int N, maxScore;
    static int[][] playResults;
    static int[] playerOrder;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        playResults = new int[N][PLAYER_NUM];
        for (int round = 0; round < N; round++) {
            String[] input = br.readLine().split(" ");

            for (int idx = 0; idx < PLAYER_NUM; idx++) {
                playResults[round][idx] = Integer.parseInt(input[idx]);
            }
        }

        solve();
    }

    private static void solve() {
        // 1. 순서 정하기
        playerOrder = new int[PLAYER_NUM];
        visited = new boolean[PLAYER_NUM];
        permutation(0);

        System.out.println(maxScore);
    }

    private static void permutation(int cnt) {
        if (cnt > 3 && playerOrder[3] != 0) {
            return;
        }

        if (cnt == PLAYER_NUM) {
            // 2. 점수 계산
            calculate();
            return;
        }

        for (int i = 0; i < PLAYER_NUM; i++) {
            if (!visited[i]) {
                visited[i] = true;
                playerOrder[cnt] = i;
                permutation(cnt + 1);
                visited[i] = false;
            }

        }
    }

    private static void calculate() {
        int score = 0;
        int outNum = 0;
        int inning = 0;
        int idx = 0;
        List<Integer> players = new ArrayList<>();

        while (inning < N) {
            int curPlayer = playerOrder[idx++];

            int result = playResults[inning][curPlayer];
            if (result <= 0) {
                // 아웃
                outNum++;
                if (outNum == MAX_OUT) {
                    outNum = 0;
                    inning++;
                    players.clear();
                }
            } else if (result < 4) {
                players.add(0);
                // 진루
                for (int i = players.size() - 1; i >= 0; i--) {
                    int nextPos = players.get(i) + result;
                    if (nextPos >= 4) {
                        score += 1;
                        players.remove(i);
                    } else {
                        players.set(i, nextPos);
                    }
                }
            } else {
                // 홈런
                score += players.size() + 1;
                players.clear();
            }

            if (idx == PLAYER_NUM) {
                idx = 0;
            }
        }

        if (score > maxScore) {
            maxScore = score;
        }
    }
}
