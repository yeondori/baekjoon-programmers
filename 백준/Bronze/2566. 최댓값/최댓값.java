import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int N = 9;
        final int M = 9;

        int max = -1;
        int row = -1;
        int col = -1;

        int[][] matrix = new int[N][M];
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                int value = Integer.parseInt(st.nextToken());
                matrix[i][j] = value;
                if (value > max) {
                    max = value;
                    row = i+1;
                    col = j+1;
                }
            }
        }
        
        System.out.println(max);
        System.out.println(row + " " + col);
    }
}