import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        String[] currentIndent = br.readLine().split(" ");
        String[] desiredIndent = br.readLine().split(" ");
        
        int[] diff = new int[N];
        for (int i = 0; i < N; i++) {
            diff[i] = Integer.parseInt(desiredIndent[i]) - Integer.parseInt(currentIndent[i]);
        }
        
        int count = 0;
        int i = 0;
        while (i < N) {
            if (diff[i] == 0) {
                i++;
                continue;
            }
            
            int sign = diff[i] > 0 ? 1 : -1;
            int j = i;
            
            // 동일한 부호의 연속된 부분 찾기
            while (j < N && diff[j] * sign > 0) {
                j++;
            }
            
            // 그룹 내에서 최소 절댓값 찾기
            int minDiff = Integer.MAX_VALUE;
            for (int k = i; k < j; k++) {
                minDiff = Math.min(minDiff, Math.abs(diff[k]));
            }
            
            // 연산 적용
            for (int k = i; k < j; k++) {
                diff[k] -= sign * minDiff;
            }
            
            count += minDiff;
        }
        
        System.out.println(count);
    }
}
