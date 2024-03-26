import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, dep, dest, answer;
    static boolean[] visited;
    static int[] route;
    static Map<Integer, ArrayList<Integer>> path = new HashMap<>();
    static class Node {
        int num;
        int cost;

        Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];
        route = new int[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            path.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
            path.computeIfAbsent(to, k -> new ArrayList<>()).add(from);
        }
        st = new StringTokenizer(br.readLine());
        dep = Integer.parseInt(st.nextToken());
        dest = Integer.parseInt(st.nextToken());

        for (Map.Entry<Integer, ArrayList<Integer>> entry : path.entrySet()) {
            Collections.sort(entry.getValue());
        }

        bfs(dep,dest);
        visited = new boolean[N+1];
        int v = route[dest];
        while(v>0) {
            visited[v] = true;
            v = route[v];
        }
        visited[dep]=false;
        bfs(dest, dep);

        System.out.println(answer);
    }

    private static void bfs(int start, int end) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(start, 0));
        visited[start] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for(int v:path.get(cur.num)) {
                if(visited[v]) continue;
                visited[v] = true;
                route[v] = cur.num;
                q.add(new Node(v, cur.cost+1));

                if(v == end) {
                    answer += cur.cost+1;
                    return;
                }
            }
        }
    }
}
