import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static ArrayList<Integer> heights;
    static int totalBuildings, visibleFromKahi, visibleFromDanbi;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        totalBuildings = Integer.parseInt(input[0]);
        visibleFromKahi = Integer.parseInt(input[1]);
        visibleFromDanbi = Integer.parseInt(input[2]);

        heights = new ArrayList<>();

        for (int i = 1; i < visibleFromKahi; i++) {
            heights.add(i);
        }
        heights.add(Math.max(visibleFromKahi, visibleFromDanbi));

        for (int i = visibleFromDanbi - 1; i >= 1; i--) {
            heights.add(i);
        }

        if (heights.size() > totalBuildings) {
            System.out.print(-1);
            return;
        }

        System.out.print(heights.get(0) + " ");
        for (int i = 0; i < totalBuildings - heights.size(); i++) {
            System.out.print(1 + " ");
        }
        for (int i = 1; i < heights.size(); i++) {
            System.out.print(heights.get(i) + " ");
        }
    }
}
