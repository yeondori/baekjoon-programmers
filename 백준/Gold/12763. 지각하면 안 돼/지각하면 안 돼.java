import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // https://dev-gorany.tistory.com/294 풀이
    static final int INF = Integer.MAX_VALUE;
    static int Size, EdgeNum, MaxTime, MaxCost;
    static List<Node>[] graph;
    static int[] dist;

    static class Node {
        int to, time, cost;

        public Node(int to, int time, int cost) {
            this.to = to;
            this.time = time;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Size = Integer.parseInt(br.readLine());
        graph = new ArrayList[Size + 1];
        for (int i = 0; i <= Size; i++) {
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        MaxTime = Integer.parseInt(st.nextToken());
        MaxCost = Integer.parseInt(st.nextToken());

        EdgeNum = Integer.parseInt(br.readLine());
        int from, to, time, cost;
        for (int i = 0; i < EdgeNum; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            time = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());

            if (time >= MaxTime || cost >= MaxCost) {
                continue;
            }
            graph[from].add(new Node(to, time, cost));
            graph[to].add(new Node(from, time, cost));
        }

        solve();
    }

    private static void solve() {
        dist = new int[Size + 1];
        for (int i = 0; i <= Size; i++) {
            dist[i] = INF;
        }

        dist[1] = 0;
        dfs(1, 0, 0);

        System.out.println(dist[Size] == INF ? -1 : dist[Size]);
    }

    private static boolean dfs(int cur, int totalTime, int totalCost) {

        if (cur == Size) {
            return true;
        }

        boolean result = false;

        for (Node adj : graph[cur]) {
            int nextTime = totalTime + adj.time;
            int nextCost = totalCost + adj.cost;
            if (nextTime > MaxTime || nextCost > MaxCost) {
                continue;
            }

            result |= dfs(adj.to, nextTime, nextCost);

            if (result && dist[adj.to] > nextCost) {
                dist[adj.to] = nextCost;
            }
        }
        return result;
    }
}