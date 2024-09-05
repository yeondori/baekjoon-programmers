import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static char[] seq;
    static boolean[][][][][] dp;
    static int S;

    static final int A = 0;
    static final int B = 1;
    static final int C = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        S = str.length();

        // 각 문자(A, B, C)의 빈도수를 세기
        int[] freq = new int[3];
        seq = new char[S + 1]; // 정답 시퀀스 저장용
        dp = new boolean[S + 1][S + 1][S + 1][3][3]; // DP 메모이제이션

        for (int i = 0; i < S; i++) {
            freq[str.charAt(i) - 'A']++;
        }

        // 해결 시도
        if (solution(freq[A], freq[B], freq[C], 0, 0)) {
            System.out.println(new String(seq, 0, S));
        } else {
            System.out.println(-1);
        }
    }

    static boolean solution(int a, int b, int c, int yd2, int yd1) {
        // 기저 조건: 모든 A, B, C를 다 사용한 경우
        if (a == 0 && b == 0 && c == 0) return true;

        // 이미 탐색한 상태면 바로 종료
        if (dp[a][b][c][yd2][yd1]) return false;
        dp[a][b][c][yd2][yd1] = true;

        // A를 선택하는 경우
        if (a > 0) {
            seq[S - a - b - c] = 'A';
            if (solution(a - 1, b, c, yd1, A)) return true;
        }

        // B를 선택하는 경우
        if (b > 0 && yd1 != B) {
            seq[S - a - b - c] = 'B';
            if (solution(a, b - 1, c, yd1, B)) return true;
        }

        // C를 선택하는 경우
        if (c > 0 && yd2 != C && yd1 != C) {
            seq[S - a - b - c] = 'C';
            if (solution(a, b, c - 1, yd1, C)) return true;
        }

        return false;
    }
}
