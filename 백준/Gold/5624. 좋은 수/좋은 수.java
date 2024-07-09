import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

//    https://jaimemin.tistory.com/876
    static final int OFFSET = 200000;
    static final int MAX_SUM = 2 * OFFSET + 1;

    static int N;
    static int[] Numbers;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        Numbers = new int[N];
        visited = new boolean[MAX_SUM];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            Numbers[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(getGoodNumbers());
    }

    private static int getGoodNumbers() {
        int result = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (visited[Numbers[i] - Numbers[j] + OFFSET]) {
                    result++;
                    break;
                }
            }

            for (int j = 0; j <= i; j++) {
                visited[Numbers[i] + Numbers[j] + OFFSET] = true;
            }
        }
        return result;
    }
}
