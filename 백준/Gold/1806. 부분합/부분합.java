import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());
        int[] sequence = new int[n];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            sequence[i] = Integer.parseInt(input[i]);
        }
        int answer = Integer.MAX_VALUE;
        int str = 0, end = 0, sum = 0;
        while (end < n) {
            while (sum < target && end < n) {
                sum += sequence[end];
                end++;
            }
            while (sum >= target && str < n) {
                answer = Math.min(answer, end - str);
                sum -= sequence[str];
                str++;
            }
        }
        System.out.println(answer==Integer.MAX_VALUE? 0:answer);
    }
}
