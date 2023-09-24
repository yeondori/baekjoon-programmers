import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long sum = 0;

        for (int i=2; i<N; i++) {
           sum += (long)i*(i-1)/2;
        }

        System.out.println(sum);
        System.out.println(3);
    }
}