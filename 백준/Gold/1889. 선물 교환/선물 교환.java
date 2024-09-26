import java.io.*;
import java.util.*;

public class Main {
    static List<Integer>[] adj;
    static int[] inDegree;
    static boolean[] visited;
    static List<Integer> result;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        adj = new ArrayList[N + 1];
        inDegree = new int[N + 1];
        visited = new boolean[N + 1];
        
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int to1 = Integer.parseInt(st.nextToken());
            int to2 = Integer.parseInt(st.nextToken());

            adj[i].add(to1);
            adj[i].add(to2);
            adj[to1].add(i);
            adj[to2].add(i);
            
            inDegree[to1]++;
            inDegree[to2]++;
        }

        result = new ArrayList<>();

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] <= 1) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            if (visited[current]) continue;
            
            visited[current] = true;
            
            for (int next : adj[current]) {
                if (visited[next]) continue;
                inDegree[next]--;

                if (inDegree[next] == 1) {
                    queue.add(next);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i] && inDegree[i] == 2) {
                result.add(i);
            }
        }

        Collections.sort(result);
        System.out.println(result.size());
        if (result.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (int student : result) {
                sb.append(student).append(" ");
            }
            System.out.println(sb.toString().trim());
        }
    }
}
