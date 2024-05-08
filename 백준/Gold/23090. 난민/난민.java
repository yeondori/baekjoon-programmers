import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    // @tedxpem 압도적 감사
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        PriorityQueue<Long> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        StringBuilder answer = new StringBuilder();

        long dist;
        long minSum = 0;
        long maxSum = 0;
        long num;
        long xSum = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());

            xSum += Math.abs(x);

            // 최소 힙과 최대 힙 유지
            if (maxHeap.isEmpty() || y < maxHeap.peek()) {
                maxHeap.add(y);
                maxSum += y;
            } else {
                minHeap.add(y);
                minSum += y;
            }

            // 힙의 크기 조정
            if (maxHeap.size() > minHeap.size() + 1) {
                num = maxHeap.poll();
                minHeap.add(num);
                maxSum -= num;
                minSum += num;
            } else if (minHeap.size() > maxHeap.size()) {
                num = minHeap.poll();
                maxHeap.add(num);
                maxSum += num;
                minSum -= num;
            }

            // 중앙값 계산
            long medianY = maxHeap.peek();

            // 거리 계산
            dist = (maxHeap.size() * medianY - maxSum) + (minSum - minHeap.size() * medianY) + xSum;

            answer.append(medianY).append(" ").append(dist).append("\n");
        }
        System.out.println(answer);
    }
}
