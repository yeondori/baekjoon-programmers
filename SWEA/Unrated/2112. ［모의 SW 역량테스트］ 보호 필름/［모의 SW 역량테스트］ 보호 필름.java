import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    final static boolean A = true;
    final static boolean B = false;
    static int testCase, filmRow, filmCol, standard, minK;
    static int[] injectionCandidateRows;
    static boolean[][] film, originFilm;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        testCase = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= testCase; tc++) {
            st = new StringTokenizer(br.readLine());
            filmRow = Integer.parseInt(st.nextToken());
            filmCol = Integer.parseInt(st.nextToken());
            standard = Integer.parseInt(st.nextToken());

            film = new boolean[filmRow][filmCol];
            originFilm = new boolean[filmRow][filmCol];
            for (int row = 0; row < filmRow; row++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int col = 0; col < filmCol; col++) {
                    film[row][col] = st.nextToken().equals("0") ? A : B;
                    originFilm[row][col] = film[row][col];
                }
            }
            minK = Integer.MAX_VALUE;
            getMinK();
            answer.append("#").append(tc).append(" ").append(minK).append("\n");
        }
        System.out.print(answer);
    }

    private static void getMinK() {
        if (pass() || standard == 1) {
            minK = 0;
            return;
        }

        for (int k = 1; k <= filmRow; k++) {
            injectionCandidateRows = new int[k];
            if (selectInjectionCandidates(0, 0, k)) {
                return;
            }
        }
    }

    private static boolean selectInjectionCandidates(int cnt, int start, int k) {
        if (cnt == k) {
            if (injection(k)) {
                return true;
            }
            reset();
            return false;
        }

        for (int i = start; i < filmRow; i++) {
            injectionCandidateRows[cnt] = i;
            if (selectInjectionCandidates(cnt + 1, i + 1, k)) {
                return true;
            }
        }
        return false;
    }

    private static boolean injection(int numCandidates) {
        for (int mask = 0; mask < (1 << numCandidates); mask++) {
            for (int i = 0; i < numCandidates; i++) {
                int row = injectionCandidateRows[i];
                if ((mask & (1 << i)) != 0) {
                    Arrays.fill(film[row], A);
                } else {
                    Arrays.fill(film[row], B);
                }
            }
            if (pass()) {
                minK = numCandidates;
                return true;
            }
        }
        return false;
    }

    private static void reset() {
        for (int row = 0; row < filmRow; row++) {
            film[row] = originFilm[row].clone();
        }
    }

    private static boolean pass() {
        for (int col = 0; col < filmCol; col++) {
            int cnt = 0;
            boolean temp = film[0][col];
            for (int row = 0; row < filmRow; row++) {
                if (film[row][col] == temp) {
                    cnt++;
                    if (cnt == standard) {
                        break;
                    }
                } else {
                    cnt = 1;
                    temp = film[row][col];
                }
            }
            if (cnt != standard) {
                return false;
            }
        }
        return true;
    }
}
