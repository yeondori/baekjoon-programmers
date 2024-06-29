import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int nodeNum, edgeNum, curA, curB, answer, ptA, ptB;
    static boolean[][] adj;
    static int[][] dist;
    static int[] points;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        nodeNum = Integer.parseInt(st.nextToken());
        edgeNum = Integer.parseInt(st.nextToken());

        adj = new boolean[nodeNum + 1][nodeNum + 1];

        for (int i = 0; i < edgeNum; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adj[from][to] = true;
            adj[to][from] = true;
        }

        solve();
    }

    private static void solve() {
        floydWarshall();
        points = new int[2];
        answer = Integer.MAX_VALUE;
        ptA = nodeNum;
        ptB = nodeNum;
        combi(0, 1);
        System.out.println(ptA + " " + ptB + " " + answer);
    }

    private static void combi(int cnt, int str) {
        if (cnt == 2) {
            int tmp = 0;

            curA = points[0];
            curB = points[1];

            for (int i = 1; i <= nodeNum; i++) {
                tmp += Math.min(dist[i][curA], dist[i][curB]) * 2;
            }

            if (answer > tmp) {
                answer = tmp;
                ptA = curA;
                ptB = curB;
            } else if (answer == tmp) {
                if (ptA == curA) {
                    ptB = Math.min(ptB, curB);
                } else if (ptA > curA) {
                    ptA = curA;
                    ptB = curB;
                }
            }
            return;
        }

        for (int i = str; i <= nodeNum; i++) {
            points[cnt] = i;
            combi(cnt + 1, i + 1);
        }
    }

    private static void floydWarshall() {
        dist = new int[nodeNum + 1][nodeNum + 1];
        for (int i = 1; i <= nodeNum; i++) {
            for (int j = 1; j <= nodeNum; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                    continue;
                }
                if (adj[i][j]) {
                    dist[i][j] = 1;
                } else {
                    dist[i][j] = 10000;
                }
            }
        }

        for (int k = 1; k <= nodeNum; k++) {
            for (int i = 1; i <= nodeNum; i++) {
                if (i == k) continue;
                for (int j = 1; j <= nodeNum; j++) {
                    if (i == j || j == k) continue;

                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
    }
}
