import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int wireNum, targetNum;
    static long maxWireLen;
    static long[] wires;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        wireNum = Integer.parseInt(st.nextToken());
        targetNum = Integer.parseInt(st.nextToken());

        wires = new long[wireNum];
        for (int i = 0; i < wireNum; i++) {
            wires[i] = Long.parseLong(br.readLine());
        }

        solve();
        System.out.println(maxWireLen);
    }

    static void solve() {
        Arrays.sort(wires);

        long str = 1;
        long end = wires[wireNum - 1];
        long mid, num;
        while(str <= end) {
            mid = (str + end) / 2;
            if (mid == 0) break;
            num = 0;
            for (int i = 0; i < wireNum; i++) {
                num += wires[i]/mid;
            }

            if (num >= targetNum) {
                str = mid + 1;
                maxWireLen = Math.max(mid, maxWireLen);
            } else {
                end = mid - 1;
            }
        }
    }
}
