import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[] groupWords = new boolean[N];
        Arrays.fill(groupWords, true);

        for (int i = 0; i < N; i++) {
            String[] str = br.readLine().split("");
            if (str.length >= 3) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < str.length-1; j++) {
                    if (!str[j].equals(str[j+1])) {
                        if (sb.indexOf(str[j]) == -1) {
                            sb.append(str[j]);
                        }
                        else {
                            groupWords[i] = false;
                            break;
                        }
                    }
                    if (sb.indexOf(str[str.length-1])!= -1) {groupWords[i] = false;}
                }
            }
        }

        int count = 0;
        for (boolean b : groupWords) {
            if (b) {count += 1; }
        }
        System.out.println(count);
    }
}