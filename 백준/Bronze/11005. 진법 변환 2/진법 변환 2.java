import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        ArrayList<Integer> num = new ArrayList<Integer>();

        while (N>=B) {
            num.add(N%B);
            N = N/B;
        }
        if (N!=0) num.add(N);
        Collections.reverse(num);

        StringBuilder sb = new StringBuilder();
        for (Integer integer : num) {
            if (B < 10 || integer<=9) {
                sb.append(integer);
            } else {sb.append((char)(integer+55));}
        }
        System.out.println(sb);
    }
}