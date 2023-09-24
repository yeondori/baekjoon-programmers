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
        Arrays.setAll(basket, num -> num + 1);

        for (int k=0; k<M; k++) {
            StringTokenizer ij = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(ij.nextToken());
            int j = Integer.parseInt(ij.nextToken());

            int tmp = basket[i-1];
            basket[i-1] = basket[j-1];
            basket[j-1] = tmp;

        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); 
        String basketStr = Arrays.toString(basket);

        for (int k=0; k<basket.length; k++)
            bw.write(basket[k] + " "); 

        bw.flush(); 
        bw.close();  
    }
}