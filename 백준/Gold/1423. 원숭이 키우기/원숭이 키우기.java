import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] countStrings = br.readLine().split(" ");
        int[] count = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            count[i] = Integer.parseInt(countStrings[i - 1]);
        }

        String[] powerStrings = br.readLine().split(" ");
        int[] power = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            power[i] = Integer.parseInt(powerStrings[i - 1]);
        }

        int D = Integer.parseInt(br.readLine());

        long initialPowerSum = 0;
        for (int i = 1; i <= N; i++) {
            initialPowerSum += (long) count[i] * power[i];
            // 훈련 가능 횟수는 D와 캐릭터 수의 최소값
            count[i] = Math.min(D, count[i]);
        }

        long[] dp = new long[D + 1];
        Arrays.fill(dp, 0);

        for (int i = 1; i < N; i++) { // 각 레벨 i에 대해
            for (int cnt = 0; cnt < count[i]; cnt++) { // 훈련 가능한 모든 캐릭터에 대해
                for (int j = D; j >= 0; j--) { // 현재까지 사용한 훈련 일수
                    // k는 훈련 후 목표 레벨
                    for (int k = i + 1; k <= N && k + j - i <= D; k++) {
                        // dp 갱신: j일 동안 훈련 후 i -> k로 훈련하여 얻는 최대 힘 증가
                        dp[k + j - i] = Math.max(dp[k + j - i], dp[j] + power[k] - power[i]);
                    }
                }
            }
        }

        long maxPower = dp[D] + initialPowerSum;
        System.out.println(maxPower);
    }
}
