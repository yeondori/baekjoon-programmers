import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int cnt = 1;
        int lastNum = 1;

        while (N>lastNum) {
            lastNum += 1 + cnt;
            cnt ++;
        }

        int reversedIdx = lastNum - N;
        if (cnt%2==0) System.out.println((cnt - reversedIdx) + "/" + (1 + reversedIdx));
        else System.out.println((1 + reversedIdx) + "/" + (cnt-reversedIdx));

    }
}