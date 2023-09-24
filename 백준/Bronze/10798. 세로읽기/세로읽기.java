import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        final int N = 5;
        int maxM = -1;
        String [][] matrix = new String [N][];


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i=0; i<N; i++) {
            matrix[i] = br.readLine().split("");
            if (matrix[i].length > maxM) maxM = matrix[i].length;
        }

        StringBuilder sb = new StringBuilder();
        for (int j=0; j<maxM; j++) {
            for (int i=0; i<N;i++) {
                if ((j < matrix[i].length)&&(!matrix[i][j].equals(" "))) sb.append(matrix[i][j]);
            }
        }
        System.out.println(sb);
    }
}