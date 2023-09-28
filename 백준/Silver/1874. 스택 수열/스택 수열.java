import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static class Stack {

        int[] stack;
        int top;
        int MAX_ARRAY_SIZE = 100000;

        public Stack() {
            this.stack = new int[MAX_ARRAY_SIZE + 1];
            this.top = 0;
            stack[top] = -1;
        }

        public void push(int x) {
            top++;
            stack[top] = x;
        }
        public int pop() {
            int x = stack[top];
            if (top>0) {top--;};
            return x;
        }
        public int top() {
            return stack[top];
        }
    }

    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Stack stack = new Stack();
        StringBuilder answer = new StringBuilder("");

        boolean isSeries = true;
        boolean isFull = false;

        int n = Integer.parseInt(br.readLine());
        int asc = 1;


        for(int i=1; i<=n; i++) {
            int x = Integer.parseInt(br.readLine());

            if (x>stack.top()) {
                if(asc>x) {
                    isFull = true;
                    isSeries = false;
                }
                while (x > stack.top() && !isFull) {
                    stack.push(asc);
                    answer.append("+").append("\n");
                    asc++;
                }
                if (x == stack.top()) {
                    stack.pop();
                    answer.append("-").append("\n");
                }

            }
            else {
                while(x<=stack.top()) {
                    stack.pop();
                    answer.append("-").append("\n");

                    if (stack.top()==-1 && isFull ) {
                        isSeries = false;
                    }
                }
            }

        }
        System.out.println(isSeries? answer.toString().trim():"NO");
    }
}