import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int T, N, minResult; // T: 테스트 케이스 개수, N: 국가 개수, result: 최소 색칠 수
    static int[] curColors, tempColors;
    static int[][] adj;
    final static int COLOR_NUM = 4;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        StringTokenizer st;

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            curColors = new int[N];
            tempColors = new int[N];
            adj = new int[N][N];
            minResult = N;

            // 국가별 색상값 받기
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                curColors[i] = Integer.parseInt(st.nextToken());
            }

            // 국가간 인접 행렬 받기
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    adj[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            //색상 배열 순열
            permutation(0);
            System.out.printf("#%d %d%n", tc, minResult);
        }


    }

    private static void permutation(int idx) {
        if (idx == N) {
            getResult();
            return;
        }

        for (int i = 0; i < COLOR_NUM; i++) {
            tempColors[idx] = i+1;
            permutation(idx + 1);
        }
    }

    private static void getResult() {
        for (int i =0; i<N; i++) {
            for (int j = i+1; j<N; j++) {
                if (adj[i][j] == 1 && sameColors(i, j)) {
                    return;
                }
            }
        }
        minResult = Math.min(minResult, calculateResult());
    }

    private static int calculateResult() {
        int result = 0;
        for (int i = 0; i<N; i++) {
            if (curColors[i] != tempColors[i]) {
                result++;
            }
        }
        return result;
    }

    private static boolean sameColors(int i, int j) {
        return tempColors[i] == tempColors[j];
    }
}
