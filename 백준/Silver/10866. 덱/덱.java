import java.io.*;

public class Main {

    public static class Deque {
        int[] deque;
        int front;
        int back;
        int arrSize;
        public Deque(int arrSize) {
            this.deque = new int[arrSize];
            this.front = arrSize-1;
            this.back = 0;
            this.arrSize = arrSize;
        }
        public void push_front(int x) {
            deque[front] = x;
            if (front==0) front = arrSize-1;
            else front--;
        }
        public void push_back(int x) {
            deque[back] = x;
            if (back==arrSize-1) back = 0;
            else back++;
        }
        public void pop_front() { //empty가 아닌 경우 front를 오른쪽으로 한칸 옮기고 출력
            if ((front+1)%arrSize != back) {
                if (front == arrSize-1) front=0;
                else front++;
                System.out.println(deque[front]);
            } else System.out.println(-1);
        }
        public void pop_back() { //empty가 아닌 경우 back을 왼쪽으로 한칸 옮기고 출력
            if ((front+1)%arrSize != back) {
                if (back == 0) back = arrSize-1;
                else back--;
                System.out.println(deque[back]);
            } else System.out.println(-1);
        }
        public void size() {
            if (front<back) {
                System.out.println(back-(front+1));
            } else {
                System.out.println(back + (arrSize-1-front));
            }
        }
        public void empty() {
            if ((front+1)%arrSize == back) { //front와 back이 이웃하는 경우
                System.out.println(1);
            } else System.out.println(0);
        }
        public void front() {
            if ((front+1)%arrSize != back) {
                int idx = (front+1)%arrSize;
                System.out.println(deque[idx]);
            } else System.out.println(-1);
        }
        public void back() {
            if ((front+1)%arrSize != back) {
                int idx = (back-1+arrSize)%arrSize;
                System.out.println(deque[idx]);
            } else System.out.println(-1);
        }
    }

    public static void main(String[] args)  throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Deque deque = new Deque(N);

        for(int i=0; i<N; i++) {
            String[] cmd = br.readLine().split(" ");

            switch (cmd[0]) {
                case "push_front":
                    int x = Integer.parseInt(cmd[1]);
                    deque.push_front(x);
                    break;

                case "push_back":
                    int y = Integer.parseInt(cmd[1]);
                    deque.push_back(y);
                    break;

                case "pop_front":
                    deque.pop_front();
                    break;

                case "pop_back":
                    deque.pop_back();
                    break;

                case "empty":
                    deque.empty();
                    break;

                case "size":
                    deque.size();
                    break;

                case "front":
                    deque.front();
                    break;

                case "back":
                    deque.back();
                    break;
             }
        }
    }
}