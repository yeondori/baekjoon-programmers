import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int [] arrScore = new int [N];

        StringTokenizer score = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            arrScore[i] = Integer.parseInt(score.nextToken());
        }

        int maxScore = Arrays.stream(arrScore).max().getAsInt();
        
        float sum = 0;
        for (int i=0; i<N; i++) {
            sum += (arrScore[i]/(float)maxScore*100);
        }
        System.out.println(sum/N);
    }
}