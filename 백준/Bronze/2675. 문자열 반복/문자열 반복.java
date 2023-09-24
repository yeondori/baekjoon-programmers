import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i =0; i<N; i++) {
            String[] RS = br.readLine().split(" ");
            int R = Integer.valueOf(RS[0]);
            String[] str = RS[1].split("");
            StringBuilder sb = new StringBuilder();

            for (int j=0; j<str.length; j++) {
                for(int k=0; k<R; k++) {
                    sb.append(str[j]);
                }
            }
            System.out.println(sb);
        }
    }
}