import java.util.Scanner;

public class Main {
    static int[][] dp = new int[201][201];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); 
        int M = sc.nextInt(); 
        int K = sc.nextInt(); 
        sc.close();

        if (comb(N + M, M) < K) {
            System.out.println(-1);
            return;
        }

        StringBuilder sb = new StringBuilder();
        while (N > 0 || M > 0) {
            if (N == 0) {
                sb.append('z');
                M--;
            } else if (M == 0) {
                sb.append('a');
                N--;
            } else {
                int leftCount = comb(N + M - 1, M);
                if (K <= leftCount) {
                    sb.append('a');
                    N--;
                } else {
                    sb.append('z');
                    K -= leftCount;
                    M--;
                }
            }
        }

        System.out.println(sb.toString());
    }

    static int comb(int n, int r) {
        if (dp[n][r] > 0) return dp[n][r];
        if (n == r || r == 0) return dp[n][r] = 1;
        return dp[n][r] = Math.min(1000000000, comb(n - 1, r - 1) + comb(n - 1, r));
    }
}
