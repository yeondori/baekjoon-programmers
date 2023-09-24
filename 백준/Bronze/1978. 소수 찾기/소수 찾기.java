import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int count = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            int A = Integer.parseInt(st.nextToken());
            int factorNum = 0;
            for(int j=1; j<=A; j++) {
                if (A%j == 0) factorNum++;
            }
            if (factorNum ==2) count++;
        }
        System.out.println(count);
    }
}