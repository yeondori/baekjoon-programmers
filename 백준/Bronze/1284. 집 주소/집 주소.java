import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = "";
        while(!(input = br.readLine()).equals("0")) {
            String[] numStr = input.split("");
            int[] numbers = Stream.of(numStr).mapToInt(Integer::parseInt).toArray();

            int width = calculateWidth(numbers);
            System.out.println(width);
        }
    }

    public static int calculateWidth(int[] numbers) {
        int width = 1;
        for (int number : numbers) {
            if (number == 0) width +=4;
            else if (number ==1) width += 2;
            else width += 3;

            width+=1;
        }

        return width;
    }
}