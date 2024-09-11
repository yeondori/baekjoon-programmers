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
        answer = numbers[len-1] - numbers[0];

        int str = 0;
        int end = 0;
        while (end < len) {
            long diff = numbers[end] - numbers[str];
            if (diff < minDiff) {
                end++;
            } else if (diff > minDiff) {
                answer = Math.min(answer, diff);
                str++;
            } else {
                answer = diff;
                break;
            }
        }

        System.out.println(answer);
    }
}
