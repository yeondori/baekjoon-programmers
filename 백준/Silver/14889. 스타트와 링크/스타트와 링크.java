import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, minDiff;
    static int sumA, sumB, diff;
    static int[][] board;
    static int[] teamA, teamB;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        minDiff = Integer.MAX_VALUE;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();
    }

    private static void solve() {
        // 1. 팀 뽑기
        teamA = new int[N / 2];
        teamB = new int[N / 2];

        int aIdx = 0;
        int bIdx = 0;
        for (int i = 1; i < (1 << N); i++) {
            if (Integer.bitCount(i) == N / 2) {
                for (int j = 0; j < N; j++) {
                    if ((i & (1 << j)) != 0) {
                        teamA[aIdx++] = j;
                    } else {
                        teamB[bIdx++] = j;
                    }
                }
                // 2. 값 계산
                calculate();

                // 3. 원복
                aIdx = 0;
                bIdx = 0;
            }
        }
        System.out.println(minDiff);
    }

    private static void calculate() {
        sumA = 0;
        sumB = 0;
        for (int i = 0; i < N / 2; i++) {
            for (int j = i + 1; j < N / 2; j++) {
                sumA += board[teamA[i]][teamA[j]] + board[teamA[j]][teamA[i]];
                sumB += board[teamB[i]][teamB[j]] + board[teamB[j]][teamB[i]];
            }
        }

        diff = Math.abs(sumA - sumB);
        if (minDiff > diff) {
            minDiff = diff;
        }
    }
}
