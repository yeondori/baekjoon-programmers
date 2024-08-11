import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    static int n;
    static long[] arr = new long[5];
    static long[][][][][][][] dp = new long[6][6][11][11][11][11][11];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine().trim());
        }
        for (long[][][][][][] dp1 : dp) {
            for (long[][][][][] dp2 : dp1) {
                for (long[][][][] dp3 : dp2) {
                    for (long[][][] dp4 : dp3) {
                        for (long[][] dp5 : dp4) {
                            for (long[] dp6 : dp5) {
                                Arrays.fill(dp6, -1);
                            }
                        }
                    }
                }
            }
        }
        System.out.println(solve(0, 0, arr[0], arr[1], arr[2], arr[3], arr[4]));
    }

    static long solve(int pprev, int prev, long x, long y, long z, long p, long q) {
        if (((pprev != 0 && prev != 0) && pprev == prev) || (x < 0 || y < 0 || z < 0 || p < 0 || q < 0)) return 0;
        if (x == 0 && y == 0 && z == 0 && p == 0 && q == 0) return 1;

        if (dp[pprev][prev][(int) x][(int) y][(int) z][(int) p][(int) q] != -1) {
            return dp[pprev][prev][(int) x][(int) y][(int) z][(int) p][(int) q];
        }

        long ret = 0;

        if (x > 0 && pprev != 1) {
            ret += solve(prev, 1, x - 1, y, z, p, q);
        }
        if (y > 0 && pprev != 2) {
            ret += solve(prev, 2, x, y - 1, z, p, q);
        }
        if (z > 0 && pprev != 3) {
            ret += solve(prev, 3, x, y, z - 1, p, q);
        }
        if (p > 0 && pprev != 4) {
            ret += solve(prev, 4, x, y, z, p - 1, q);
        }
        if (q > 0 && pprev != 5) {
            ret += solve(prev, 5, x, y, z, p, q - 1);
        }

        dp[pprev][prev][(int) x][(int) y][(int) z][(int) p][(int) q] = ret;
        return ret;
    }
}
