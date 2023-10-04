import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {


    public static class Queue {

        int[] queue;
        int top;
        int bottom;
        int MAX_SIZE = 10_000;

        public Queue() {
            this.queue = new int[MAX_SIZE];
            this.top = 0;
            this.bottom = 0;
        }

        public void push(int x){
            queue[top] = x;
            top++;
        }
        public void pop(){
            if (top!=bottom) {
                System.out.println(queue[bottom]);
                bottom++;
                queue = Arrays.copyOfRange(queue, bottom, MAX_SIZE);
                top --;
                bottom--;
            } else System.out.println(-1);
        }
        public void size(){
            System.out.println(top);
        }
        public void empty(){
            System.out.println(top==bottom? 1:0);
        }
        public void front(){
            System.out.println(top==bottom? -1:queue[bottom]);
        }
        public void back() {
            System.out.println(top==bottom? -1:queue[top-1]);
        }
    }

    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Queue queue = new Queue();

        int num = Integer.parseInt(br.readLine());

        for(int i=0; i<num; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();

            switch (cmd) {
                case "push":
                    int x = Integer.parseInt(st.nextToken());
                    queue.push(x);
                    break;

                case "pop":
                    queue.pop();
                    break;

                case "size":
                    queue.size();
                    break;

                case "empty":
                    queue.empty();
                    break;

                case "front":
                    queue.front();
                    break;

                case "back":
                    queue.back();
                    break;
            }

        }
    }
}