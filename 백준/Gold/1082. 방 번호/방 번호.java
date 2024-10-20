import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
 
        int N = Integer.parseInt(br.readLine());
 
        int[] stationery = new int[N];
        int min = 50;
        int index = -1;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int temp = Integer.parseInt(st.nextToken());
            stationery[i] = temp;
 
            if (min >= stationery[i]) {
                min = stationery[i];
                index = i;
            }
        }
 
        int money = Integer.parseInt(br.readLine());
        char[] digits = new char[51];
        int cnt = 0;
        while (money >= min) {
            digits[cnt++] = (char) (index + '0');
            money -= min;
        }
 
        int start = 0;
        for (int i = 0; i < cnt; i++) {
            for (int j = N - 1; j >= 0; j--) {
                if (stationery[j] <= money + min) {
                    digits[i] = (char) (j + '0');
                    money += min - stationery[j];
                    break;
                }
            }
 
            if (digits[start] == '0') {
                start++;
                money += min;
            }
        }
 
        if (start == cnt) {
            sb.append("0\n");
        } else {
            for (int i = start; i < cnt; i++) {
                sb.append(digits[i]);
            }
            sb.append("\n");
        }
 
        System.out.print(sb.toString());
        br.close();
    }
 
}