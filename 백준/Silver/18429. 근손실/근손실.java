import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int WEIGHT = 500;
    static int kitNum, decrease, answer;
    static int[] kits;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        kitNum = Integer.parseInt(st.nextToken());
        decrease = Integer.parseInt(st.nextToken());

        kits = new int[kitNum];
        visited = new boolean[kitNum];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < kitNum; i++) {
            kits[i] = Integer.parseInt(st.nextToken());
        }

        workOut(0,  WEIGHT);
        System.out.println(answer);
    }

    private static void workOut(int cnt, int curWeight) {
        if (curWeight < WEIGHT) {
            return;
        }

        if (cnt == kitNum) {
            answer++;
            return;
        }

        for (int i = 0; i < kitNum; i++) {
            if (!visited[i]) {
                visited[i] = true;
                workOut(cnt + 1, curWeight - decrease + kits[i]);
                visited[i] = false;
            }
        }
    }

}
