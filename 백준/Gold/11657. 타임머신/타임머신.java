import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final long MAX = Long.MAX_VALUE;
    static int nodeNum, linkNum;

    static class Link implements Comparable<Link> {
        int from, to, weight;

        Link(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Link o) {
            return this.weight - o.weight;
        }
    }

    static Link[] links;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        nodeNum = Integer.parseInt(st.nextToken());
        linkNum = Integer.parseInt(st.nextToken());

        links = new Link[linkNum];
        for (int i = 0; i < linkNum; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            links[i] = new Link(from, to, weight);
        }

        solve();
    }

    static void solve() {
        long[] dist = new long[nodeNum + 1];
        Arrays.fill(dist, MAX);
        dist[1] = 0;
        
        for (int i = 0; i <= nodeNum; i++) {
            for (int j = 0; j < linkNum; j++) {
                if (dist[links[j].from] == MAX) continue;

                if (dist[links[j].to] > dist[links[j].from] + links[j].weight) {
                    dist[links[j].to] = dist[links[j].from] + links[j].weight;

                    if (i == nodeNum) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        StringBuilder answer = new StringBuilder("");
        for (int i = 2; i <= nodeNum; i++) {
            answer.append(dist[i] == MAX? -1 : dist[i])
                    .append("\n");
        }
        System.out.println(answer);
    }
}
