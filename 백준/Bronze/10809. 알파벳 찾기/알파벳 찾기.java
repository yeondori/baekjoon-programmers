import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();

        for (int i=0; i<alphabet.length(); i++) { sb.append(word.indexOf(alphabet.charAt(i)) + " "); }
        System.out.println(sb);
    }
}