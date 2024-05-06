import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] workTime;
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        workTime = new int[N + 1];
        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int to = 1; to <= N; to++) {
            st = new StringTokenizer(br.readLine());

            workTime[to] = Integer.parseInt(st.nextToken());

            int childNum = Integer.parseInt(st.nextToken());
            for (int j = 0; j < childNum; j++) {
                int from = Integer.parseInt(st.nextToken());
                graph[to].add(from);
            }
        }
        System.out.println(solve());
    }

    private static int solve() {
        int[] endTime = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            endTime[i] = workTime[i];
        }

        for (int i = 1; i <= N; i++) {
            for (int adj : graph[i]) {
                endTime[i] = Math.max(endTime[i], workTime[i] + endTime[adj]);
            }
        }

        int maxEndTime = 0;
        for (int i = 1; i <= N; i++) {
            maxEndTime = Math.max(maxEndTime, endTime[i]);
        }

        return maxEndTime;
    }
}
