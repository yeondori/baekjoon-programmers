import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static Long[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        numbers = new Long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Long.parseLong(st.nextToken());
        }

        solve();
    }

    private static void solve() {
        int answer = 0;
        Arrays.sort(numbers);

        int left, right;
        for (int i = 0; i < N; i++) {
            left = 0;
            right = N - 1;
            while (left < right) {
                if (left == i) {
                    left++;
                } else if (right == i) {
                    right--;
                }

                if (left >= right) {
                    break;
                }

                if (numbers[left] + numbers[right] > numbers[i]) {
                    right--;
                } else if (numbers[left] + numbers[right] < numbers[i]) {
                    left++;
                } else {
                    answer++;
                    break;
                }
            }
        }

        System.out.println(answer);
    }
}
