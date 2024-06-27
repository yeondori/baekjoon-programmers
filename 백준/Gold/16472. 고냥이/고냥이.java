import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] input = br.readLine().toCharArray();

        int answer = 0, str = 0;
        Map<Character, Integer> alphaCount = new HashMap<>();

        char strAlpha, endAlpha;
        for (int end = 0, len = input.length; end < len; end++) {
            endAlpha = input[end];
            alphaCount.put(endAlpha, alphaCount.getOrDefault(endAlpha, 0) + 1);

            while (alphaCount.size() > N) {
                strAlpha = input[str];
                alphaCount.put(strAlpha, alphaCount.get(strAlpha) - 1);

                if (alphaCount.get(strAlpha) == 0) {
                    alphaCount.remove(strAlpha);
                }
                str++;
            }
            answer = Math.max(answer, end - str + 1);
        }
        System.out.println(answer);
    }
}
