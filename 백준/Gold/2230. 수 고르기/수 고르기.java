
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int len, minDiff;
    static long answer;
    static long[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        len = Integer.parseInt(st.nextToken());
        minDiff = Integer.parseInt(st.nextToken());

        numbers = new long[len];
        for (int i = 0; i < len; i++) {
            numbers[i] = Long.parseLong(br.readLine());
        }
        solve();
    }

    static void solve() {
        Arrays.sort(numbers);
        answer = Long.MAX_VALUE;  // 최소값을 찾기 위한 초기화

        int str = 0;
        int end = 0;
        while (end < len) {
            long diff = numbers[end] - numbers[str];

            // 조건에 따라 포인터 이동
            if (diff >= minDiff) {
                answer = Math.min(answer, diff);
                str++;  // 조건을 만족하면 str 증가
            } else {
                end++;  // 조건을 만족하지 않으면 end 증가
            }

            // minDiff를 찾으면 더 이상 탐색할 필요 없음
            if (answer == minDiff) break;
        }

        System.out.println(answer);
    }
}