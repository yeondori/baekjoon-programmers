import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static long number;
    static int digit, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        number = Long.parseLong(input[0]);
        digit = input[0].length();
        K = Integer.parseInt(input[1]);

        if (digit < K) {
            System.out.println("5".repeat(K));
        } else {
            solve();
        }
    }

    private static void solve() {
        long nextNumber = number + 1;
        int cnt = countFive(nextNumber);
        if (cnt >= K) {
            System.out.println(nextNumber);
        } else {
            findNext(nextNumber);
        }
    }

    private static void findNext(long curNum) {
        String curStr = curNum + "";
        char[] cur = curStr.toCharArray();

        int curCnt = countFive(curNum);
        int rIdx = 1;
        int len = curStr.length();
        while (curCnt < K) {
            if (cur[len - rIdx] < '5') {
                cur[len - rIdx] = '5';
                rIdx++;
                if (countFive(cur) >= K) {
                    break;
                }
            } else {
                int diff = cur[len - rIdx] - '0';
                long dv = (long) Math.pow(10, rIdx);
                curNum += dv;
                curNum -= (diff * dv / 10);
                curStr = curNum + "";
                cur = curStr.toCharArray();
                rIdx = 1;
            }
            curCnt = countFive(curNum);
        }
        for (int i = 0; i < cur.length; i++) {
            System.out.print(cur[i]);
        }
    }

    private static int countFive(long nextNumber) {
        String num = nextNumber + "";
        int cnt = 0;
        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) == '5')
                cnt++;
        }
        return cnt;
    }

    private static int countFive(char[] nextNumber) {
        int cnt = 0;
        for (int i = 0; i < nextNumber.length; i++) {
            if (nextNumber[i] == '5')
                cnt++;
        }
        return cnt;
    }
}