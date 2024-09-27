import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] score;
    static int[] maxScore, minScore, tempMax, tempMin;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        score = new int[N][3];
        maxScore = new int[3];
        minScore = new int[3];
        tempMax = new int[3];
        tempMin = new int[3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                score[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.arraycopy(score[0], 0, maxScore, 0, 3);
        System.arraycopy(score[0], 0, minScore, 0, 3);

        for (int i = 1; i < N; i++) {
            tempMax[0] = Math.max(maxScore[0], maxScore[1]) + score[i][0];
            tempMax[1] = Math.max(Math.max(maxScore[0], maxScore[1]), maxScore[2]) + score[i][1];
            tempMax[2] = Math.max(maxScore[1], maxScore[2]) + score[i][2];

            tempMin[0] = Math.min(minScore[0], minScore[1]) + score[i][0];
            tempMin[1] = Math.min(Math.min(minScore[0], minScore[1]), minScore[2]) + score[i][1];
            tempMin[2] = Math.min(minScore[1], minScore[2]) + score[i][2];

            System.arraycopy(tempMax, 0, maxScore, 0, 3);
            System.arraycopy(tempMin, 0, minScore, 0, 3);
        }

        int maxResult = Math.max(Math.max(maxScore[0], maxScore[1]), maxScore[2]);
        int minResult = Math.min(Math.min(minScore[0], minScore[1]), minScore[2]);

        System.out.println(maxResult + " " + minResult);
    }
}
