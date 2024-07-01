import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K, minUnchecked, maxUnchecked;
    static int[][] inAndOut;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        inAndOut = new int[N][2];
        int in, out;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            out = Integer.parseInt(st.nextToken());
            in = Integer.parseInt(st.nextToken());

            inAndOut[i][0] = in;
            inAndOut[i][1] = out;
        }

        minUnchecked = findMin();
        maxUnchecked = findMax();

        System.out.println(minUnchecked + " " + maxUnchecked);
    }

    private static int findMax() {
        // 최대: 미검표한 사람을 우선적으로 내보내기
        int answer = 0;

        int checked = inAndOut[0][0];
        int unchecked = 0;

        int out, curOut;
        for (int i = 1; i < N; i++) {

            out = inAndOut[i][1];
          
            if (out <= unchecked) {
                unchecked -= out;
                answer += out;
            } else {
                curOut = unchecked;
                answer += curOut;
                unchecked = 0;
                checked -= (out - curOut);
            }
            unchecked += inAndOut[i][0];
           
            if (i % K == 0) {
                checked += unchecked;
                unchecked = 0;
            }
        }
        return answer;
    }

    private static int findMin() {
        // 최소: 검표한 사람을 우선적으로 내보내기
        int answer = 0;

        int checked = inAndOut[0][0];
        int unchecked = 0;

        int out, curOut;
        for (int i = 1; i < N; i++) {

            out = inAndOut[i][1];

            if (out <= checked) {
                checked -= out;
            } else {
                curOut = checked;
                checked = 0;
                unchecked -= (out - curOut);
                answer += (out - curOut);
            }

            unchecked += inAndOut[i][0];
            
            if (i % K == 0) {
                checked += unchecked;
                unchecked = 0;
            }
        }
        return answer;
    }
}
