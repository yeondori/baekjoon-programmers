import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int MAX_VALUE = Integer.MAX_VALUE;
    static int[] distance;
    static final String INFINITE = "INF";
    static int VertexNum, EdgeNum, start;

    static class Vertex implements Comparable<Vertex> {
        int to, weight;

        public Vertex(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Vertex o) {
            return this.weight - o.weight;
        }
    }

    static List<Vertex>[] edges;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        VertexNum = Integer.parseInt(st.nextToken());
        EdgeNum = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());

        edges = new List[VertexNum + 1];
        for (int i = 0; i <= VertexNum; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < EdgeNum; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges[from].add(new Vertex(to, weight));
        }

        solve();
    }

    static void solve() {
        dijkstra();
        for (int i = 1; i <= VertexNum; i++) {
            System.out.println(distance[i] == MAX_VALUE? INFINITE:distance[i]);
        }
    }

    static void dijkstra() {
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        distance = new int[VertexNum + 1];
        boolean[] visited = new boolean[VertexNum + 1];
        Arrays.fill(distance, MAX_VALUE);

        pq.add(new Vertex(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Vertex cur = pq.poll();

            if (visited[cur.to]) continue;
            visited[cur.to] = true;

            for (Vertex adj : edges[cur.to]) {
                int adjIdx = adj.to;
                int adjWeight = adj.weight;
                
                if ( !visited[adjIdx] && distance[adjIdx] > adjWeight + distance[cur.to]) {
                    distance[adjIdx] = adjWeight + distance[cur.to];
                    pq.add(new Vertex(adjIdx, distance[adjIdx]));
                }
            }
        }
    }
}
