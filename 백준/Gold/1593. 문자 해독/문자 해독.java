import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int wordNum, lineNum;
    static Map<Character, Integer> word, visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        wordNum = Integer.parseInt(st.nextToken());
        lineNum = Integer.parseInt(st.nextToken());

        String input = br.readLine();
        word = new HashMap<>();
        for (int i = 0; i < wordNum; i++) {
            char alpha = input.charAt(i);
            int freq = word.getOrDefault(alpha, 0);
            word.put(alpha, freq + 1);
        }

        solve(br.readLine());
    }

    static void solve(String line) {
        int answer = 0;
        visit = new HashMap<>();

        for (int i = 0; i < wordNum; i++) {
            char alpha = line.charAt(i);
            int freq = visit.getOrDefault(alpha, 0);
            visit.put(alpha, freq + 1);
        }

        if (isSameWord()) {
            answer++;
        }

        for (int i = wordNum; i < lineNum; i++) {
            char alpha = line.charAt(i);

            int freq = visit.getOrDefault(alpha, 0);
            visit.put(alpha, freq + 1);

            alpha = line.charAt(i - wordNum);
            freq = visit.get(alpha);
            visit.put(alpha, freq - 1);

            if (isSameWord()) answer++;
        }

        System.out.println(answer);
    }

    static boolean isSameWord() {
        for (Map.Entry<Character, Integer> entry : word.entrySet()) {
            char key = entry.getKey();
            int freq = entry.getValue();

            Integer visitFreq = visit.get(key);
            if (visitFreq == null || visitFreq != freq) return false;
        }
        return true;
    }
}
