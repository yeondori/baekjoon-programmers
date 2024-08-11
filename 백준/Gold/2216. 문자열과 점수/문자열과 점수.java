import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] input = br.readLine().split(" ");
        int A = Integer.parseInt(input[0]);
        int B = Integer.parseInt(input[1]);
        int C = Integer.parseInt(input[2]);
        
        String X = br.readLine();
        String Y = br.readLine();
        
        int m = X.length();
        int n = Y.length();
        
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i * B;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j * B;
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    dp[i][j] = dp[i-1][j-1] + A;
                } else {
                    dp[i][j] = dp[i-1][j-1] + C;
                }
                dp[i][j] = Math.max(dp[i][j], dp[i-1][j] + B);  
                dp[i][j] = Math.max(dp[i][j], dp[i][j-1] + B);
            }
        }
        
        System.out.println(dp[m][n]);
    }
}
