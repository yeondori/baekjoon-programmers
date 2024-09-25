import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int wordNum, maxLen;
    static String answer;
    static Set<String> dictionary;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        wordNum = Integer.parseInt(st.nextToken());
        String startWord = st.nextToken();

        dictionary = new HashSet<>();
        for (int i = 0; i < wordNum; i++) {
            String word = br.readLine();
            dictionary.add(word);
        }

        solve(startWord);
        System.out.println(answer);
    }

    static void solve(String startWord) {
        Queue<String> q = new ArrayDeque<>();
        q.offer(startWord);
        answer = startWord;
        maxLen = startWord.length();

        while (!q.isEmpty()) {
            String curWord = q.poll();
            int curLen = curWord.length();

            if (curLen > maxLen) {
                maxLen = curLen;
                answer = curWord;
            }

            for (char alpha = 'a'; alpha <= 'z'; alpha++) {
                for (int i = 0; i <= curLen; i++) {
                    String newWord = curWord.substring(0, i) + alpha + curWord.substring(i);
                    if (dictionary.contains(newWord)) {
                        q.offer(newWord);
                        dictionary.remove(newWord); 
                    }
                }
            }
        }
    }
}
