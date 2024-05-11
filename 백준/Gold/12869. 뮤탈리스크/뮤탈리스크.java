import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] scv = new int[3];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            scv[i] = Integer.parseInt(st.nextToken());
        }

        int[][][] dp = new int[61][61][61];
        System.out.println(attack(scv[0], scv[1], scv[2], dp));
    }

    public static int attack(int a, int b, int c, int[][][] dp) {
        if (a <= 0 && b <= 0 && c <= 0) {
            return 0;
        }

        if (dp[a][b][c] != 0) {
            return dp[a][b][c];
        }

        int minAttack = Integer.MAX_VALUE;

        minAttack = Math.min(minAttack, attack(Math.max(a - 9, 0), Math.max(b - 3, 0), Math.max(c - 1, 0), dp) + 1);
        minAttack = Math.min(minAttack, attack(Math.max(a - 9, 0), Math.max(b - 1, 0), Math.max(c - 3, 0), dp) + 1);
        minAttack = Math.min(minAttack, attack(Math.max(a - 3, 0), Math.max(b - 1, 0), Math.max(c - 9, 0), dp) + 1);
        minAttack = Math.min(minAttack, attack(Math.max(a - 3, 0), Math.max(b - 9, 0), Math.max(c - 1, 0), dp) + 1);
        minAttack = Math.min(minAttack, attack(Math.max(a - 1, 0), Math.max(b - 9, 0), Math.max(c - 3, 0), dp) + 1);
        minAttack = Math.min(minAttack, attack(Math.max(a - 1, 0), Math.max(b - 3, 0), Math.max(c - 9, 0), dp) + 1);

        dp[a][b][c] = minAttack;
        return minAttack;
    }
}
