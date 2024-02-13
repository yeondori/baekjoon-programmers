import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int answer = -1; // 초기값을 -1로 설정하여 조건에 해당하는 경우가 없을 때의 처리를 위해 변경합니다.

        // 5킬로그램 봉지로 가능한 경우부터 탐색합니다.
        for (int i = 0; i <= N / 5; i++) {
            int remainder = N - (5 * i);
            if (remainder % 3 == 0) {
                int bags = i + remainder / 3;
                if (answer == -1 || bags < answer) {
                    answer = bags;
                }
            }
        }

        System.out.println(answer);
    }
}
