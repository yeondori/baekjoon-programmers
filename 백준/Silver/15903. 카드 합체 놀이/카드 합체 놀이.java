import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static PriorityQueue<Long> cards = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cards.offer(Long.parseLong(st.nextToken()));
        }

        for (int j = 0; j < M; j++) {
            Long num1 = cards.poll();
            Long num2 = cards.poll();
            long mergeNum = num1 + num2;
            cards.offer(mergeNum);
            cards.offer(mergeNum);
        }

        long totalSum = 0;
        while (!cards.isEmpty()) {
            totalSum += cards.poll();
        }
        System.out.println(totalSum);
    }
}
