import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder answer = new StringBuilder("<");

        ArrayList arr = new ArrayList();
        int N = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int ptr = 0;

        for (int i=1; i<=N; i++) arr.add(i);

        while(!arr.isEmpty()) {
            ptr += x-1;
            ptr = ptr%arr.size();

            answer.append(arr.get(ptr));
            if (arr.size()>1) answer.append(", ");
            arr.remove(ptr);
        }
        answer.append(">");
        System.out.println(answer);
    }
}