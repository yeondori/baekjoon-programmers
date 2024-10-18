import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int n, k, answer;
    static int[] weight;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine()); 

        n = (int) Math.pow(2, k + 1) - 1;
        weight = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 2; i <= n; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }

        dfs(1, 0);
        
        System.out.println(answer);
    }

    static int dfs(int idx, int h) {
        if (h == k) {
            answer += weight[idx]; 
            return weight[idx];
        }

        int left = dfs(idx * 2, h + 1); 
        int right = dfs(idx * 2 + 1, h + 1); 

        answer += weight[idx] + Math.abs(left - right);

        return weight[idx] + Math.max(left, right);
    }
}
