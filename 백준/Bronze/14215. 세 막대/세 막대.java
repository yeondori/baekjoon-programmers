import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] legs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(legs);
        while (legs[2]>=legs[0]+legs[1]) {
            legs[2] -= 1;
        }

        System.out.println(Arrays.stream(legs).sum());
    }
}