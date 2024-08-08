import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<Integer>[] graph;
    static boolean[] isIndoor;
    static long result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String A = br.readLine();

        isIndoor = new boolean[N + 1];
        for (int i = 0; i < N; i++) {
            isIndoor[i + 1] = A.charAt(i) == '1';
        }

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        for (int i = 1; i <= N; i++) {
            if (isIndoor[i]) {
                dfs(i, i, 0);
            }
        }

        System.out.println(result);
    }

    static void dfs(int start, int current, int parent) {
        if (isIndoor[current] && current != start) {
            result++;
            return;
        }

        for (int next : graph[current]) {
            if (next != parent) {
                dfs(start, next, current);
            }
        }
    }
}