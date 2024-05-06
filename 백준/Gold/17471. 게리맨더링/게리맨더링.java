import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, minDiff;
    static int[] populations;
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        populations = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            populations[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int from = 1; from <= N; from++) {
            st = new StringTokenizer(br.readLine());
            int adjNum = Integer.parseInt(st.nextToken());
            for (int r = 0; r < adjNum; r++) {
                int to = Integer.parseInt(st.nextToken());
                graph[from].add(to);
                graph[to].add(from);
            }
        }

        minDiff = Integer.MAX_VALUE;
        solve(1, new boolean[N + 1], new ArrayList<>(), new ArrayList<>());
        System.out.println(minDiff == Integer.MAX_VALUE ? -1 : minDiff);
    }

    private static void solve(int node, boolean[] visited, List<Integer> groupA, List<Integer> groupB) {
        if (node > N) {
            if (!groupA.isEmpty() && !groupB.isEmpty() && validGroup(groupA) && validGroup(groupB)) {
                minDiff = Math.min(minDiff, getDiff(groupA, groupB));
            }
            return;
        }

        // 현재 노드를 groupA에 포함시키는 경우
        visited[node] = true;
        groupA.add(node);
        solve(node + 1, visited, groupA, groupB);
        groupA.remove(groupA.size() - 1);
        visited[node] = false;

        // 현재 노드를 groupB에 포함시키는 경우
        groupB.add(node);
        solve(node + 1, visited, groupA, groupB);
        groupB.remove(groupB.size() - 1);
    }

    private static boolean validGroup(List<Integer> group) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> q = new ArrayDeque<>();
        int node = group.get(0);
        q.add(node);
        visited[node] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int adj : graph[cur]) {
                if (!visited[adj] && group.contains(adj)) {
                    visited[adj] = true;
                    q.add(adj);
                }
            }
        }

        for (int i : group) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }

    private static int getDiff(List<Integer> groupA, List<Integer> groupB) {
        int aPop = 0, bPop = 0;
        for (int a : groupA) {
            aPop += populations[a];
        }

        for (int b : groupB) {
            bPop += populations[b];
        }
        return Math.abs(aPop - bPop);
    }
}