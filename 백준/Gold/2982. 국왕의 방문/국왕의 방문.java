import java.util.*;
import java.io.*;

public class Main {
    private static final int INF = Integer.MAX_VALUE;
    private static int N, M, A, B, K, G;
    private static int[] kingVisitArr, dist;
    private static int[][] timeArr;
    private static Go[][] goArray;

    private static class Go {
        int start;
        int end;

        public Go(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        kingVisitArr = new int[1001];
        timeArr = new int[1001][1001];
        goArray = new Go[1001][1001];
        dist = new int[1001];

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < G; i++) {
            kingVisitArr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            timeArr[start][end] = time;
            timeArr[end][start] = time;

            goArray[start][end] = new Go(0, 0);
            goArray[end][start] = new Go(0, 0);
        }

        int sum = 0;
        for (int i = 0; i < G - 1; i++) {
            int s = kingVisitArr[i];
            int e = kingVisitArr[i + 1];

            goArray[s][e].start = sum;
            goArray[e][s].start = sum;

            goArray[s][e].end = sum + timeArr[s][e];
            goArray[e][s].end = sum + timeArr[e][s];
            sum += timeArr[s][e];
        }

        dijkstra();
        System.out.println(dist[B] - K);
    }

    private static void dijkstra() {
        PriorityQueue<Cross> pque = new PriorityQueue<>();
        Arrays.fill(dist, INF);

        dist[A] = K;
        pque.offer(new Cross(A, K));

        while (!pque.isEmpty()) {
            Cross pollCross = pque.poll();

            for (int i = 1; i <= N; i++) {
                if (timeArr[pollCross.road][i] > 0) {
                    int newDist;
                    if (dist[pollCross.road] >= goArray[pollCross.road][i].start && dist[pollCross.road] <= goArray[pollCross.road][i].end) {
                        newDist = goArray[pollCross.road][i].end + timeArr[pollCross.road][i];
                    } else {
                        newDist = dist[pollCross.road] + timeArr[pollCross.road][i];
                    }
                    if (newDist < dist[i]) {
                        dist[i] = newDist;
                        pque.offer(new Cross(i, dist[i]));
                    }
                }
            }
        }
    }

    private static class Cross implements Comparable<Cross> {
        int road;
        int time;

        public Cross(int road, int time) {
            this.road = road;
            this.time = time;
        }

        @Override
        public int compareTo(Cross o) {
            return Integer.compare(this.time, o.time);
        }
    }
}
