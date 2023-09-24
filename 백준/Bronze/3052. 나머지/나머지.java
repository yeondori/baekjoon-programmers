import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        final int input = 10;
        final int quotient = 42;

        int[] remainders = new int[input];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i= 0; i<input; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int value = Integer.parseInt(st.nextToken());

            int remainder = value%quotient;
            remainders[i] = remainder;
        }

        int [] distRemainders = Arrays.stream(remainders).distinct().toArray();

        System.out.println(distRemainders.length);
    }
}