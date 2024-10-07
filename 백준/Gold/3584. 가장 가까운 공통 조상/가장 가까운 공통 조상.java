import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int testCaseNum, nodeNum;
    static int[] parents, depth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        testCaseNum = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < testCaseNum; tc++) {

            nodeNum = Integer.parseInt(br.readLine());
            parents = new int[nodeNum + 1];
            depth = new int[nodeNum + 1];

            for (int i = 1; i < nodeNum; i++) {
                st = new StringTokenizer(br.readLine());
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());
                parents[child] = parent; 
            }

            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            System.out.println(findParent(a, b));
        }
    }

    static int findParent(int a, int b) {
        if (depth[a] == 0) setDepth(a);
        if (depth[b] == 0) setDepth(b);

        while (depth[a] > depth[b]) a = parents[a];
        while (depth[b] > depth[a]) b = parents[b];

        while (a != b) {
            a = parents[a];
            b = parents[b];
        }
        return a;
    }

    static void setDepth(int node) {
        if (node == 0 || depth[node] != 0) return;
        if (parents[node] != 0 && depth[parents[node]] == 0) {
            setDepth(parents[node]);  
        }
        depth[node] = depth[parents[node]] + 1;
    }
}
