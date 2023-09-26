import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static class Stack {

        int[] stack;
        int top;
        int MAX_ARRAY_SIZE = 10_000;

        public Stack() {
            this.stack = new int[MAX_ARRAY_SIZE+1];
            this.top = 0;
            stack[top] = -1;
        }

        public void push(int x) {
            top++;
            stack[top] = x;
        }
        public void pop() {
            System.out.println(stack[top]);
            if (top>0) {top--;};
        }
        public void size() {
            System.out.println(top);
        }
        public void empty() {
            System.out.println(top==0? 1:0);
        }

        public void top() {
            System.out.println(stack[top]);
        }
    }


    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack stack = new Stack();

        int num = Integer.parseInt(br.readLine());

        for(int i=0; i<num; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();

            switch (cmd) {
                case "push": cmd.equals("push");
                    int x = Integer.parseInt(st.nextToken());
                    stack.push(x);
                    break;

                case "pop": cmd.equals("pop");
                    stack.pop();
                    break;

                case "size": cmd.equals("size");
                    stack.size();
                    break;

                case "empty": cmd.equals("empty");
                    stack.empty();
                    break;

                case "top": cmd.equals("top");
                    stack.top();
                    break;
            }
        }
    }
}