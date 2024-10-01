import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        int[] fruits = new int[N];
        for (int i = 0; i < N; i++) {
            fruits[i] = Integer.parseInt(input[i]);
        }

        int start = 0;
        int maxCount = 0;
        HashMap<Integer, Integer> fruitCount = new HashMap<>();

        for (int end = 0; end < N; end++) {
            int fruit = fruits[end];
            fruitCount.put(fruit, fruitCount.getOrDefault(fruit, 0) + 1);

            while (fruitCount.size() > 2) {
                int removeFruit = fruits[start];
                fruitCount.put(removeFruit, fruitCount.get(removeFruit) - 1);
                if (fruitCount.get(removeFruit) == 0) {
                    fruitCount.remove(removeFruit);
                }
                start++;
            }

            maxCount = Math.max(maxCount, end - start + 1);
        }

        System.out.println(maxCount);
    }
}
