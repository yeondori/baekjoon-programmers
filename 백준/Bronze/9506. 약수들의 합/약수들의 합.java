import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            int N = Integer.parseInt(br.readLine());

            if (N==-1) break;
            else {
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i < N; i++) if (N % i == 0) sb.append(i).append(" ");
                String[] factorStr = sb.toString().split(" ");

                int[] factors = Arrays.stream(factorStr)
                        .mapToInt(Integer::parseInt)
                        .toArray();

                if (Arrays.stream(factors).sum() == N) {
                    System.out.printf("%d = ",N);
                    for(int factor : factors) {
                        if (factor != factors[factors.length-1]) {
                            System.out.printf("%d + ", factor);
                        } else System.out.println(factor);
                    }
                }
                else {
                    System.out.printf("%d is NOT perfect.\n",N);
                }
            }
        }
    }
}