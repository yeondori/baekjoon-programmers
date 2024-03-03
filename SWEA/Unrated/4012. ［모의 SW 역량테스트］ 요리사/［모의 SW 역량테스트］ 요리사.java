import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    static int T, N;
    static int[] ingA, ingB;
    static int foodA, foodB, minDiff;
    static int[][] synergy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder printer = new StringBuilder();

        T =Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            synergy = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    synergy[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            minDiff = Integer.MAX_VALUE;
            getAnswer();
            printer.append("#").append(tc).append(" ").append(minDiff).append("\n");
        }
        System.out.println(printer);
    }

    private static void getAnswer() {
        // 조합으로 재료 2개 고르기
        chooseIngredients();
        // 최솟값 구하고 갱신하기
    }

    private static void chooseIngredients() {
        for (int i = 0; i < (1<<N); i++) {
            int idxA = 0, idxB = 0;
            ingA = new int[N / 2];
            ingB = new int[N / 2];
            if (Integer.bitCount(i) == N/2) {
                for (int j = 0; j < N; j++) {
                    if ((i & (1 << j)) != 0) {
                        ingA[idxA++] = j;
                    } else {
                        ingB[idxB++] = j;
                    }
                }

                // 계산 로직
                getMinDiff();
            }
        }
    }

    private static void getMinDiff() {
        foodA = getSynergy(ingA);
        foodB = getSynergy(ingB);
        int diff = Math.abs(foodA - foodB);
        if (diff < minDiff) {
            minDiff = diff;
        }
    }

    private static int getSynergy(int[] ing) {
        int syn = 0;
        for (int i = 0; i < N/2; i++) {
            for (int j = 0; j < N/2; j++) {
                if (i == j) {
                    continue;
                }
                syn += synergy[ing[i]][ing[j]];
            }
        }
        return syn;
    }
}
