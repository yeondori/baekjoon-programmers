import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int ans = (int) Math.pow((1 + Math.pow(2,N)),2);
        System.out.println(ans);
    }
}