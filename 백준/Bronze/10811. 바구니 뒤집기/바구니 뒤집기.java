import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer nm = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(nm.nextToken());
        int M = Integer.parseInt(nm.nextToken());

        int[] arr = new int [N];
        Arrays.setAll(arr, num -> num+1);

        for (int k=0; k<M; k++) {
            StringTokenizer ij = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(ij.nextToken());
            int j = Integer.parseInt(ij.nextToken());

            int[] switchArr = Arrays.copyOfRange(arr, i-1, j);
            for (; i<=j; i++) {
                arr[i-1] = switchArr[j-i];
            }
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        for (int k=0;k<arr.length;k++) {
            sb.append(arr[k] + " ");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}