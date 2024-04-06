import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    final static int END_CONDITION = 4;

    static int NodeNum, EdgeNum;
    static boolean endFlag;
    static boolean[] visited;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        NodeNum = Integer.parseInt(st.nextToken());
        EdgeNum = Integer.parseInt(st.nextToken());
        visited = new boolean[NodeNum];
        graph = new ArrayList[NodeNum];
        for (int i = 0; i < NodeNum; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int e = 0; e < EdgeNum; e++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            graph[to].add(from);
        }

        areTheyFriends();
    }

    private static void areTheyFriends() {

        for (int node = 0; node < NodeNum; node++) {
            visited[node] = true;
            dfs(node, 0);
            visited[node] = false;

            if(endFlag) {
                break;
            }
        }

        System.out.println(endFlag?1:0);
    }

    private static void dfs(int node, int cnt) {
        if (cnt==END_CONDITION) {
            endFlag = true;
            return;
        }

        for (int nextNode : graph[node]) {
            if(visited[nextNode]) continue;
            visited[nextNode] = true;
            dfs(nextNode, cnt +1);
            visited[nextNode] = false;
        }
    }
}
