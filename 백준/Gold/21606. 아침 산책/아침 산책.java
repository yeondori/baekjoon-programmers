import java.util.*;
import java.io.*;

//https://prodyou.tistory.com/16
public class Main {
    static ArrayList<Integer>[] graph;
    static boolean[] isIndoor;
    static boolean[] visited;
    static long result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String A = br.readLine();

        isIndoor = new boolean[N + 1];
        visited = new boolean[N + 1];
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
            if (isIndoor[u] && isIndoor[v]) {
                result += 2; // 실내끼리 인접했을 경우 경로를 2개 더해준다.
            }
        }

        for (int i = 1; i <= N; i++) {
            if (!isIndoor[i] && !visited[i]) { // 실외를 기준으로 생각
                int indoor = dfs(i);
                result += (long) indoor * (indoor - 1);
            }
        }

        System.out.println(result);
    }

    static int dfs(int v) {
        visited[v] = true;
        int indoor = 0;
        for (int next : graph[v]) {
            if (isIndoor[next]) {
                indoor++;
            } else if (!visited[next]) {
                indoor += dfs(next);
            }
        }
        return indoor;
    }
}