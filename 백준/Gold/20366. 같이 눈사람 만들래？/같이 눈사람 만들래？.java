import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, minDiff;
    static int[] H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        H = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            H[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(H);
        solve();
    }

    private static void solve() {
        minDiff = Integer.MAX_VALUE;

        find:
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int elsa = H[i] + H[j];
                int start = 0;
                int end = N - 1;
                while (start < end) {
                    if (start == i || start == j) {
                        start++;
                        continue;
                    }
                    if (end == i || end == j) {
                        end--;
                        continue;
                    }
                    int anna = H[start] + H[end];
                    minDiff = Math.min(Math.abs(anna - elsa), minDiff);

                    if (elsa > anna) {
                        start++;
                    } else if (elsa < anna) {
                        end--;
                    } else {
                        minDiff = 0;
                        break find;
                    }
                }
            }
        }

        System.out.println(minDiff);
    }
}
