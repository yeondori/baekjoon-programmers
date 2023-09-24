import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] num = br.readLine().split(" ");
        StringBuilder reverseNum1 = new StringBuilder(num[0]).reverse();
        StringBuilder reverseNum2 = new StringBuilder(num[1]).reverse();

        int num1 = Integer.parseInt(String.valueOf(reverseNum1));
        int num2 = Integer.parseInt(String.valueOf(reverseNum2));

        System.out.println(num1<num2? num2 : num1);
    }
}