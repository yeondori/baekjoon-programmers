import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] X = new int[3];
        int[] Y = new int[3];



        for (int i=0; i<3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            X[i] = x;
            Y[i] = y;
        }

        Arrays.sort(X);
        Arrays.sort(Y);

        int ansX = X[1] == X[0]? X[2] : X[0];
        int ansY = Y[1] == Y[0]? Y[2] : Y[0];

        System.out.printf("%d %d",ansX, ansY);
    }
}