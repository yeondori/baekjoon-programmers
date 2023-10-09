import java.io.*;

public class Main {

    public static void main(String[] args)  throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();

        StringBuilder bars = new StringBuilder();

        for (int i = 0; i < input.length - 1; i++) {  // laser와 bar 구분
            if ((input[i] == '(') && (input[i + 1] == ')')) {
                i++;
                input[i] = 'l';
            }
            bars.append(input[i]);
            if (i == input.length - 2) bars.append(input[i + 1]);
        }

        int barNum = 0;
        int totalBarNum = 0;

        for (int i = 0; i < bars.length(); i++) {
            if (bars.charAt(i) == '(') barNum++;
            else if (bars.charAt(i) == ')') {
                barNum--;
                totalBarNum++;
            } else {
                totalBarNum += barNum;
            }
        }
        System.out.println(totalBarNum);
    }
}