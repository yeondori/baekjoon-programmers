import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, SellLimit;
    static class Painting implements Comparable<Painting> {
        int height;
        int price;

        public Painting(int height, int price) {
            this.height = height;
            this.price = price;
        }

        @Override
        public int compareTo(Painting o) {
            return this.height - o.height;
        }
    }
    static Painting[] paintings;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        SellLimit = Integer.parseInt(st.nextToken());

        paintings = new Painting[N+1];
        paintings[0] = new Painting(0, 0);
        int height, price;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            height = Integer.parseInt(st.nextToken());
            price = Integer.parseInt(st.nextToken());

            paintings[i] = new Painting(height, price);
        }

        Arrays.sort(paintings);

        int[] dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            int res = search(i);
            dp[i] = Math.max(dp[i - 1], dp[res] + paintings[i].price);
        }
        System.out.println(dp[N]);
    }

    private static int search(int i) {
        int start = 0, end = i, mid = 0, res = 0, diff;

        while (start <= end) {
            mid = (start + end) / 2;
            diff = paintings[i].height - paintings[mid].height;

            if (diff < SellLimit) {
                end = mid - 1;
            } else {
                res = mid;
                start = mid + 1;
            }
        }
        return res;
    }
}
