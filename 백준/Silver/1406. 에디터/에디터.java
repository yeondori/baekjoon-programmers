import java.io.*;
import java.util.LinkedList;
import java.util.ListIterator;

public class Main {

    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        LinkedList<Character> editor = new LinkedList<Character>();

        for (char ch: br.readLine().toCharArray()) {
            editor.add(ch);
        }
        ListIterator<Character> cursor = editor.listIterator();
        while(cursor.hasNext()) {
            cursor.next();
        }

        int n = Integer.parseInt(br.readLine());
        for (int i=0; i<n; i++) {
            char[] cmd = br.readLine().toCharArray();

            switch (cmd[0]) {
                case 'L' :
                    if (cursor.hasPrevious()) cursor.previous();
                    break;

                case 'D' :
                    if (cursor.hasNext()) cursor.next();
                    break;

                case 'B' :
                    if (cursor.hasPrevious()) {
                        cursor.previous();
                        cursor.remove();
                    }
                    break;

                case 'P' :
                    char input = cmd[2];
                    cursor.add(input);
                    break;
            }
        }

        for (Character ch : editor) {
            bw.write(ch);
        }

        bw.flush();
        bw.close();
    }
}