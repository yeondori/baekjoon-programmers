import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        char[] stars = new char[2*N-1];
        Arrays.fill(stars, ' ');


        for (int i=0; i<N; i++) {
            int n = N-1;
            stars[n-i] = '*';
            stars[n+i] = '*';

            StringBuilder sb = new StringBuilder();
            for (int j=0; j<=n+i; j++) {
                sb.append(stars[j]);
            };
            System.out.println(sb);
        }

        for (int i=N-1; i>0; i--) {
            int n = N-1;
            stars[n-i] = ' ';
            stars[n+i] = ' ';

            StringBuilder sb = new StringBuilder();
            for (int j=0; j<n+i; j++) {
                sb.append(stars[j]);
            };
            System.out.println(sb);
        }
    }
}