import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<Integer>[] graph;
    static boolean[] isIndoor;
    static long result = 0;
    static int indoorCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String A = br.readLine();

        isIndoor = new boolean[N + 1];
        for (int i = 0; i < N; i++) {
            isIndoor[i + 1] = A.charAt(i) == '1';
            if (isIndoor[i + 1]) indoorCount++;
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

        if (indoorCount <= 1) {
            System.out.println(0);
            return;
        }

        dfs(1, 0);

        System.out.println(result);
    }

    static int dfs(int node, int parent) {
        int indoorChildrenSum = 0;

        for (int child : graph[node]) {
            if (child != parent) {
                int indoorChildren = dfs(child, node);
                if (isIndoor[node]) {
                    result += indoorChildren;
                }
                indoorChildrenSum += indoorChildren;
            }
        }

        if (isIndoor[node]) {
            indoorChildrenSum++;
            if (parent != 0) {
                result += indoorCount - indoorChildrenSum;
            }
        }

        return indoorChildrenSum;
    }
}