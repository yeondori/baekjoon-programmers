import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] sequence = new int[N];
        int[] nge = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(0);


        for (int i = 1; i < N; i++) {
            while(!stack.empty() && (sequence[i] > sequence[stack.peek()])) {
                nge[stack.pop()] = sequence[i];
            }
            stack.push(i);
        }

        while(!stack.empty()) {
            nge[stack.pop()] = -1;
        }

        StringBuilder answer = new StringBuilder();

        for(int n: nge) {
            answer.append(n).append(" ");
        }
        System.out.println(answer.toString().trim());

    }
}