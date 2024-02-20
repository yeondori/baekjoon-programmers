import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Wire implements Comparable<Wire> {
        int from;
        int to;

        public Wire(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public int compareTo(Wire o) {
            return this.from - o.from;
        }
    }

    static Wire[] wires;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        wires = new Wire[N];
        dp = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            wires[i] = new Wire(from, to);
        }

        Arrays.sort(wires);
        findMinRemoval();
    }

    static void findMinRemoval() {
        Arrays.fill(dp, 1);

        for (int i = 1; i < wires.length; i++) {
            for (int j = 0; j < i; j++) {
                if (wires[i].to > wires[j].to) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int max = Arrays.stream(dp).max().getAsInt();
        System.out.println(wires.length - max);
    }
}
