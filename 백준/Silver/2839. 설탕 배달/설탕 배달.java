import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if (N>=5) {
            if (N%5==0) {
                System.out.println(N/5);
            }
            else {
                int Q = N/5;
                int R = N%5;

                while(true) {
                    if (R % 3 == 0) {
                        System.out.println(Q + (R / 3));
                        break;
                    } else {
                        Q--;
                        R += 5;
                    }
                    if (R==N) {
                        System.out.println(((R % 3) == 0) ? (R / 3) : -1);
                        break;
                    }
                }
            }
        }
        else System.out.println(N==3? 1: -1);

    }
}