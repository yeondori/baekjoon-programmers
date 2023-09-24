import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String N_str = br.readLine();
        int N_length = N_str.length();
        int N = Integer.parseInt(N_str);
        boolean isAnwser = false;

        for(int i = (N - (N_length * 9)); i < N; i++) {
            int number = i;
            int sum = 0;

            while(number != 0) {
                sum += number % 10;
                number /= 10;
            }

            if(sum + i == N) {
                System.out.println(i);
                isAnwser = true;
                break;
            }
        }
        if (!isAnwser) System.out.println(0);
    }
}