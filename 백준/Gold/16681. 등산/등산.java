import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//https://alpha.velog.io/@taeho97/BaekJoon-16681-%EB%93%B1%EC%82%B0-Java
public class Main {

    static int NodeNum, EdgeNum, Cost, Profit;
    static int[] heights;

    static class Point implements Comparable<Point> {
        int next;
        long dist;

        public Point(int next, long dist) {
            this.next = next;
            this.dist = dist;
        }

        @Override
        public int compareTo(Point o) {
            return Long.compare(this.dist, o.dist);
        }
    }

    static List<Point>[] edges;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        NodeNum = Integer.parseInt(st.nextToken());
        EdgeNum = Integer.parseInt(st.nextToken());
        Cost = Integer.parseInt(st.nextToken());
        Profit = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        heights = new int[NodeNum + 1];
        for (int i = 1; i <= NodeNum; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        edges = new ArrayList[NodeNum + 1];
        for (int i = 0; i <= NodeNum; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < EdgeNum; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long dist = Long.parseLong(st.nextToken());

            edges[from].add(new Point(to, dist));
            edges[to].add(new Point(from, dist));
        }

        solve();
    }

    static void solve() {
        long[] str = dijkstra(1);
        long[] end = dijkstra(NodeNum);

        long answer = Long.MIN_VALUE;
        for (int i = 1; i <= NodeNum; i++) {
            if (str[i] == Long.MAX_VALUE || end[i] == Long.MAX_VALUE) continue;
            answer = Math.max(answer, heights[i] * Profit - (str[i] + end[i]) * Cost);
        }
        System.out.println(answer == Long.MIN_VALUE ? "Impossible" : answer);
    }

    static long[] dijkstra(int start) {
        long[] distance = new long[NodeNum + 1];
        Arrays.fill(distance, Long.MAX_VALUE);

        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Point cur = pq.poll();
            if (distance[cur.next] < cur.dist) continue;

            for (Point adj : edges[cur.next]) {
                if (heights[cur.next] >= heights[adj.next]) continue;

                long newDist = cur.dist + adj.dist;
                if (distance[adj.next] > newDist) {
                    distance[adj.next] = newDist;
                    pq.offer(new Point(adj.next, newDist));
                }
            }
        }

        return distance;
    }
}
