import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] changes = new int[]{25, 10, 5, 1};
        int N = Integer.parseInt(br.readLine());

        for (int i=0; i<N; i++) {
            int C = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
            for(int change : changes) {
                if (C>=change) {
                    sb.append(C/change).append(" ");
                    C%=change;
                } else sb.append(0).append(" ");
            }
            System.out.println(sb);
        }
    }
}