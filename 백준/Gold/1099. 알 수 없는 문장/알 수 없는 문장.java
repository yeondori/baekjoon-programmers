import java.util.*;

public class Main {
    static String sentence;
    static int N;
    static String[] words;
    static int[] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        sentence = sc.next(); 
        N = sc.nextInt();
        words = new String[N];
        
        for (int i = 0; i < N; i++) {
            words[i] = sc.next(); 
        }

        int len = sentence.length();
        dp = new int[len + 1]; 
        Arrays.fill(dp, Integer.MAX_VALUE); 
        dp[0] = 0; 
        
        for (int i = 0; i < len; i++) {
            if (dp[i] == Integer.MAX_VALUE) continue; 
            
            for (String word : words) {
                int wordLen = word.length();
                if (i + wordLen <= len) { // 문장 내에서 단어를 확인할 수 있는 경우
                    String sub = sentence.substring(i, i + wordLen);
                    int cost = calculateCost(sub, word);
                    if (cost != -1) { // 유효한 단어인 경우
                        dp[i + wordLen] = Math.min(dp[i + wordLen], dp[i] + cost);
                    }
                }
            }
        }
        
        int result = dp[len] == Integer.MAX_VALUE ? -1 : dp[len];
        System.out.println(result);
    }

    private static int calculateCost(String a, String b) {
        if (a.length() != b.length()) return -1;
        
        char[] aArr = a.toCharArray();
        char[] bArr = b.toCharArray();
        Arrays.sort(aArr);
        Arrays.sort(bArr);
        
        if (!Arrays.equals(aArr, bArr)) return -1; // 재배열 후에도 서로 다른 경우
        
        int cost = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) cost++; // 위치가 다른 경우 비용 증가
        }
        
        return cost;
    }
}
