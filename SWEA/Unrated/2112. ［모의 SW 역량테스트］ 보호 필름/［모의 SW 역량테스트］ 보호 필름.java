import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    static int T, D, W, K, answer;
    static int[][] film, copyFilm;

    public static void main(String[] args) throws IOException {
        StringBuilder printer = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            film = new int[D][W];
            copyFilm = new int[D][W];
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    film[i][j] = Integer.parseInt(st.nextToken());
                    copyFilm[i][j] = film[i][j];
                }
            }
            answer = Integer.MAX_VALUE;
            getAnswer();
            printer.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(printer);
    }

    private static void getAnswer() {
        // 합격 여부 체크
        boolean allPass = isAllPass(film);
        if (allPass) {
            answer = 0;
            return;
        }
        // 막 투여
        for (int i = 1; i <= (1 << D); i++) {
            boolean[] selected = new boolean[D];
            for (int j = 0; j < D; j++) {
                if ((i & (1 << j)) != 0) {
                    selected[j] = true;
                }
            }
            // dfs
            dfs(selected, 0, 0);
            reset();
        }
    }

    private static void reset() {
        for (int i = 0; i < D; i++) {
            copyFilm[i] = film[i].clone();
        }
    }

    private static void dfs(boolean[] selected, int idx, int cnt) {
        if (cnt >= answer) {
            return;
        }

        if (idx == D) {
            if (isAllPass(copyFilm)) {
                answer = Math.min(answer, cnt);
            }
            return;
        }

        if (selected[idx]) {
            Arrays.fill(copyFilm[idx], 0);
            dfs(selected, idx + 1, cnt + 1);
            Arrays.fill(copyFilm[idx], 1);
            dfs(selected, idx + 1, cnt + 1);
        } else {
            dfs(selected, idx + 1, cnt);
        }
    }

    private static boolean isAllPass(int[][] film) {
        boolean allPass = true;
        for (int i = 0; i < W; i++) {
            if (!checkSequential(i, film)) {
                allPass = false;
                break;
            }
        }
        return allPass;
    }

    private static boolean checkSequential(int col, int[][] film) {
        int target = film[0][col];
        int cnt = 0;
        for (int i = 0; i < D; i++) {
            if (film[i][col] == target) {
                cnt++;
            } else {
                cnt = 1;
                target = film[i][col];
            }
            if (cnt == K) {
                return true;
            }
        }
        return false;
    }
}
