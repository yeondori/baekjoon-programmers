import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        bw.write(1- Integer.parseInt(st.nextToken())+ " ");
        bw.write(1-Integer.parseInt(st.nextToken())+ " ");
        bw.write(2-Integer.parseInt(st.nextToken())+ " ");
        bw.write(2-Integer.parseInt(st.nextToken())+ " ");
        bw.write(2-Integer.parseInt(st.nextToken())+ " ");
        bw.write(8-Integer.parseInt(st.nextToken())+ "");

        bw.flush();
        bw.close();
    }
}