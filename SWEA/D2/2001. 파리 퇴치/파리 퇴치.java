import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int T, M, N, answer;
    static int[][] flies;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            flies = new int[M][M];

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    flies[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            answer = 0;
            search(0, 0);
            System.out.printf("#%d %d%n", tc, answer);
        }

    }

    private static void search(int x, int y) {
        for (int i = x; i < M; i++) {
            for (int j = y; j < M; j++) {
                swat(i, j);
            }
        }
    }

    private static void swat(int x, int y) {
        int fliesNum = 0;
        for (int i = x; i < x + N; i++) {
            for (int j = y; j < y + N; j++) {
                if (i >= M || j >= M) {
                    return;
                }
                fliesNum += flies[i][j];
            }
        }
        answer = Math.max(answer, fliesNum);
    }
}
