import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution {
    static int T;
    static HashMap<Long, Long> prefixSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        prefixSum = new HashMap<>();
        long sum = 0;
        for (long i = 0; i < 10; i++) {
            prefixSum.put(i, sum += i);
        }

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());

            long ps = getPrefixSum(a, b);
            answer.append("#").append(tc).append(" ").append(ps).append("\n");
        }
        System.out.println(answer);
    }

    private static long getPrefixSum(long a, long b) {
        if (a > 0) {
            return F(b) - F(a-1);
        } else {
            return F(b) - F(a);
        }
    }

    private static long F(long n) {
        if (prefixSum.containsKey(n)) {
            return prefixSum.get(n);
        }

        long digit = getDigit(n);
        long F = F(n - 1 - n % digit);
        long G = (n / digit) * (n % digit + 1) + F(n % digit);
        long num = F + G;

        prefixSum.put(n, num);
        return num;
    }

    private static long getDigit(long n) {
        long digit = 1;
        while(n>=10) {
            digit = digit*10;
            n /= 10;
        }
        return digit;
    }
}
