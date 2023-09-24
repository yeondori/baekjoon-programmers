import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer nm = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(nm.nextToken());
        int M = Integer.parseInt(nm.nextToken());

        int[][] A = new int[N][M];
        int[][] B = new int[N][M];

        for (int i=0;i<N;i++) {A[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();}
        for (int i=0;i<N;i++) {B[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();}



        for (int i=0; i<N; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j=0; j<M; j++) {
                sb.append(A[i][j] + B[i][j]);
                sb.append(" ");
            }
            System.out.println(sb);
        }
    }
}