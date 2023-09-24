import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = "";
        while (!(input=br.readLine()).equals("0 0 0")) {
            int[] legs = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
            System.out.println(isTriangle(legs));
        }

    }

    static String isTriangle(int[] legs) {
        Arrays.sort(legs);
        String triangle = "";
        if (legs[2]<legs[0]+legs[1]) {
            triangle = (legs[0]==legs[2])? "Equilateral" :
                    (legs[0]==legs[1]||legs[1]==legs[2])?  "Isosceles" : "Scalene";
        } else triangle = "Invalid";

        return triangle;
    }
}