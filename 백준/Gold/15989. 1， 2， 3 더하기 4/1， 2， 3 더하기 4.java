import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//https://velog.io/@hyeokkr/%EB%B0%B1%EC%A4%8015989-123-%EB%8D%94%ED%95%98%EA%B8%B0-4-with-Java
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine().trim());

        // dp 배열 초기화
        int[][] dp = new int[10001][4];  // n은 10,000까지 가능, k는 1, 2, 3을 의미
        dp[1][1] = dp[2][1] = dp[2][2] = dp[3][1] = dp[3][2] = dp[3][3] = 1;

        for (int i = 4; i <= 10000; ++i) {
            dp[i][1] = dp[i - 1][1];
            dp[i][2] = dp[i - 2][1] + dp[i - 2][2];
            dp[i][3] = dp[i - 3][1] + dp[i - 3][2] + dp[i - 3][3];
        }

        for (int i = 0; i < test_case; i++) {
            int n = Integer.parseInt(br.readLine().trim());
            System.out.println(dp[n][1] + dp[n][2] + dp[n][3]);
        }
    }
}
