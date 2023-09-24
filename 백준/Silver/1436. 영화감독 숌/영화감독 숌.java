import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long num = 666;
        int cnt = 0;

        while(true) {
            String numToStr = Long.toString(num);
            if (numToStr.contains("666")) {
                cnt++;
                if (cnt == N) {
                    System.out.println(numToStr);
                    break;
                }
            }
            num++;
        }
    }
}