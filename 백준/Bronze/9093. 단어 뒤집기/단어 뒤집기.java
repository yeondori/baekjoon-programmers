import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());

        for(int i=0; i<num; i++) {
            String[] input = br.readLine().split(" ");
            StringBuilder flippedInput = new StringBuilder("");

            for(int j=0; j<input.length; j++) {
                flippedInput.append(flip(input[j])).append(" ");
            }
            
            System.out.println(flippedInput.toString().trim());
        }
    }

    public static String flip(String word) {
        StringBuilder flippedWord = new StringBuilder("");

        for (int i=word.length()-1; i>=0; i--) {
            flippedWord.append(word.charAt(i));
        }

        return flippedWord.toString();
    }
}