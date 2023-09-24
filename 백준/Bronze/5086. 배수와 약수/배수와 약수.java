import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        while(!(input = br.readLine()).equals("0 0")){

            String[] ab = input.split(" ");

            int a = Integer.parseInt(ab[0]);
            int b = Integer.parseInt(ab[1]);

            if (a >= b) {
                System.out.println((a % b == 0) ? "multiple" : "neither");
            } else {
                System.out.println((b % a == 0) ? "factor" : "neither");
            }
        }
        }
    }