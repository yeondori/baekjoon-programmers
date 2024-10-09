import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int honeyCombSize, days, edgeNum;
    static int[][] honeyComb;
    static int[] growth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        honeyCombSize = Integer.parseInt(st.nextToken());
        days = Integer.parseInt(st.nextToken());
        edgeNum = 2 * honeyCombSize - 1;

        honeyComb = new int[honeyCombSize][honeyCombSize];
        for (int i = 0; i < honeyCombSize; i++) {
            for (int j = 0; j < honeyCombSize; j++) {
                honeyComb[i][j] = 1; 
            }
        }

        int[] growthSum = new int[edgeNum];

        for (int day = 0; day < days; day++) {
            st = new StringTokenizer(br.readLine());
            int zeroCount = Integer.parseInt(st.nextToken());
            int oneCount = Integer.parseInt(st.nextToken());
            int twoCount = Integer.parseInt(st.nextToken());

            for (int i = 0; i < zeroCount; i++) growthSum[i] += 0;
            for (int i = zeroCount; i < zeroCount + oneCount; i++) growthSum[i] += 1;
            for (int i = zeroCount + oneCount; i < edgeNum; i++) growthSum[i] += 2;
        }

        int idx = 0;
        for (int i = honeyCombSize - 1; i >= 0; i--) {
            honeyComb[i][0] += growthSum[idx++];
        }
        for (int j = 1; j < honeyCombSize; j++) {
            honeyComb[0][j] += growthSum[idx++];
        }

        for (int i = 1; i < honeyCombSize; i++) {
            for (int j = 1; j < honeyCombSize; j++) {
                honeyComb[i][j] = Math.max(honeyComb[i - 1][j], 
                                           Math.max(honeyComb[i][j - 1], honeyComb[i - 1][j - 1]));
            }
        }

        printHoneyComb();
    }

    static void printHoneyComb() {
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < honeyCombSize; i++) {
            for (int j = 0; j < honeyCombSize; j++) {
                answer.append(honeyComb[i][j]).append(" ");
            }
            answer.append("\n");
        }
        System.out.print(answer);
    }
}
