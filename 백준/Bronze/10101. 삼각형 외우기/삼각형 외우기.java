import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] triangle = new int[3];
        int sumAngle = 0;

        for (int i=0; i<3 ;i++) {
            int angle = Integer.parseInt(br.readLine());

            triangle[i] = angle;
            sumAngle += angle;
        }

        Arrays.sort(triangle);

        if (sumAngle==180) {
            System.out.println(triangle[2]==60? "Equilateral":
                    (triangle[0]==triangle[1]||triangle[1]==triangle[2])? "Isosceles" : "Scalene");
        }   else System.out.println("Error");;
    }
}