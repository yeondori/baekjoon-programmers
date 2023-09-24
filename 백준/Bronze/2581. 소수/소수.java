import java.io.*;
import java.util.Arrays;
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i=N; i <= M; i++){
            int factorNum = 0;
            for (int j = 1; j <= i; j++) {
                if (i % j == 0) factorNum++;
            }
            if (factorNum == 2) sb.append(i).append(" ");
        }

        if (!(sb.toString().equals(""))) {

            String[] factorStr = sb.toString().split(" ");
            int[] factors = Arrays.stream(factorStr)
                    .mapToInt(Integer::parseInt)
                    .toArray();
            System.out.println(Arrays.stream(factors).sum());
            System.out.println(Arrays.stream(factors).min().isPresent()? Arrays.stream(factors).min().getAsInt(): "-1");
        } else System.out.println(-1);

    }
}