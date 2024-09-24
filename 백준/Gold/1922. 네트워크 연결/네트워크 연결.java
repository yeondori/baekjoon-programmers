import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int nodeNum, linkNum;

    static class Node implements Comparable<Node> {
        int to, weight;

        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    static List<Node>[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        nodeNum = Integer.parseInt(br.readLine());
        linkNum = Integer.parseInt(br.readLine());

        nodes = new ArrayList[nodeNum + 1];
        for (int i = 0; i <= nodeNum; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < linkNum; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            nodes[from].add(new Node(to, weight));
            nodes[to].add(new Node(from, weight));
        }

        System.out.println(solve());
    }

    static int solve() {
        int answer = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[nodeNum + 1];

        pq.add(new Node(1, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();


            if (visited[cur.to]) continue;
            visited[cur.to] = true;
            answer += cur.weight;

            for (Node adj : nodes[cur.to]) {

                if (visited[adj.to]) continue;
                pq.add(adj);
            }
        }
        return answer;
    }
}
