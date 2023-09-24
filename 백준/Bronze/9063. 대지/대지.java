import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] X = new int[N];
        int[] Y = new int[N];

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            X[i] = x;
            Y[i] = y;
        }

        Arrays.sort(X);
        Arrays.sort(Y);

        int width = X[N-1] - X[0];
        int length = Y[N-1] - Y[0];

        System.out.println((long)width*length);
    }
}