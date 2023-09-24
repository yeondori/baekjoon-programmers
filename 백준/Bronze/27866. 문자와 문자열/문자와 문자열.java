import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();
        int idx = Integer.parseInt(br.readLine())-1;

        System.out.println(word.charAt(idx));
    }
}
