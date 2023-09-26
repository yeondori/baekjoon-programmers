import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());

        for(int i=0; i<num; i++) {
            String input = br.readLine();
            int isVps = 0;
            for (String s: input.split("")) {
                if (s.equals("(")) isVps++;
                else if (s.equals(")")&&(isVps==0)) {
                    isVps = -50;
                    break;
                } else isVps--;
             }

            System.out.println(isVps==0? "YES": "NO");
        }
    }
}