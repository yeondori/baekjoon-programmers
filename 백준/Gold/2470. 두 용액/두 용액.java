import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static long[] liquids;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        liquids = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            liquids[i] = Long.parseLong(st.nextToken());
        }

        solve();
    }

    static void solve() {
        Arrays.sort(liquids);

        long minFeature = Long.MAX_VALUE;

        long liquid1 = 0L, liquid2 = 0L;

        int left = 0;
        int right = N-1;
        long feat;
        while (left < right && right < N) {

            feat = liquids[left] + liquids[right];

            if (Math.abs(feat) < Math.abs(minFeature)) {
                minFeature = feat;
                liquid1 = liquids[left];
                liquid2 = liquids[right];
            }

            if (feat > 0) {
                right--;
            }
            else {
                left++;
            }
        }
        System.out.println(liquid1 + " " + liquid2);
    }
}
