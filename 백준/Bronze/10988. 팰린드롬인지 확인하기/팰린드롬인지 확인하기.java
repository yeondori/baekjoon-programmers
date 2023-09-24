import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int n = input.length();
        int result = 1;

        for (int i=0; i<n/2; i++) {
            if (input.charAt(i) == input.charAt(n-1-i)) {
                result*=1;
            } else result*=0;
        }
        System.out.println(result);
    }
}