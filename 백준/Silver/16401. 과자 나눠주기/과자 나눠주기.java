import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int c = Integer.parseInt(input[0]);
        int n = Integer.parseInt(input[1]);

        int[] cookies = Stream.of(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int result = 0;

        Arrays.sort(cookies);
        int start = 1;
        int end = cookies[n-1];

        while (start<=end) {
            int cookieNum = 0;
            int mid = (start + end)/2;

            for (int i = 0; i <n; i++) {
                cookieNum += cookies[i]/mid;
            }
            if (cookieNum >= c) {
                result = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(result);
    }
}
