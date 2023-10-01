import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Stack<Integer> stack = new Stack<>();
        StringBuilder answer = new StringBuilder("");

        stack.push(0);

        boolean isSeries = true;

        int n = Integer.parseInt(br.readLine());
        int k = 1;

        for(int i=1; i<=n; i++) {
            int x = Integer.parseInt(br.readLine());

            while(x>stack.peek() && k<=n) {
                stack.push(k);
                answer.append("+").append("\n");
                k++;
            }
            while(x<stack.peek() && !stack.empty()) {
                stack.pop();
                answer.append("-").append("\n");
            }
            if (x== stack.peek()) {
                stack.pop();
                answer.append("-").append("\n");
            } else {
                isSeries = false;
            }
        }
        System.out.println(isSeries? answer.toString().trim():"NO");
    }
}
