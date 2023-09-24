import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer nm = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(nm.nextToken());
        int M = Integer.parseInt(nm.nextToken());

        int[] basket = new int[N];
        Arrays.fill(basket, 0);

        for (int m=0; m<M; m++) {
            StringTokenizer ijk = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(ijk.nextToken());
            int j = Integer.parseInt(ijk.nextToken());
            int k = Integer.parseInt(ijk.nextToken());

            for (; i<=j; i++) {
                basket[i-1] = k;
            }

        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));  
        String basketStr = Arrays.toString(basket); 

        for (int i=0; i<basket.length; i++)
            bw.write(basket[i] + " "); 

        bw.flush();   
        bw.close(); 
    }
}