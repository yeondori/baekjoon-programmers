import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    final static int INF = 987654321;
    static int T, N, M, W;

    static class Edge {
        int from;
        int to;
        int cost;

        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static List<Edge> edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            edges = new ArrayList<>();

            for (int i = 0; i < M + W; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                if (i < M) {
                    edges.add(new Edge(from, to, cost));
                    edges.add(new Edge(to, from, cost));
                } else {
                    edges.add(new Edge(from, to, -cost));
                }
            }
            System.out.println(isReversed(1) ? "YES" : "NO");
        }
    }

    private static boolean isReversed(int start) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        // N-1번의 순회를 하여 최단 거리 갱신
        for (int i = 0; i < N - 1; i++) {
            for (Edge edge : edges) {
                if (dist[edge.to] > dist[edge.from] + edge.cost) {
                    dist[edge.to] = dist[edge.from] + edge.cost;
                }
            }
        }

        // 음수 사이클 검사
        for (Edge edge : edges) {
            if (dist[edge.to] > dist[edge.from] + edge.cost) {
                return true; // 음수 사이클 존재
            }
        }
        return false;
    }
}
