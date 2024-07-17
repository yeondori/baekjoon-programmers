import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int[] children;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        children = new int[N];
        for (int i = 0; i < N; i++) {
            children[i] = Integer.parseInt(br.readLine());
        }

        solve();
    }

    private static void solve() {
        int[] dp = new int[N];
        int max = 0;
        for (int cur = 0; cur < N; cur++) {
            dp[cur] = 1;
            for (int before = 0; before < cur; before++) {
                if (children[cur] > children[before]) {
                    dp[cur] = Math.max(dp[cur], dp[before] + 1);
                    if (dp[cur] > max) {
                        max = dp[cur];
                    }
                }
            }
        }

        System.out.println(N - max);
    }
}
