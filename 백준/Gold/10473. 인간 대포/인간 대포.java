import java.io.*;
import java.util.*;

public class Main {

    static class Coordinate {
        float x;
        float y;

        public Coordinate(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {
        int next;
        float time;

        public Edge(int destination, float time) {
            this.next = destination;
            this.time = time;
        }

        @Override
        public int compareTo(Edge o) {
            return Float.compare(this.time, o.time);
        }
    }

    static Coordinate[] coordinates;
    static ArrayList<Edge>[] adj;
    static float[] minTime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        float startX = Float.parseFloat(st.nextToken());
        float startY = Float.parseFloat(st.nextToken());
        Coordinate start = new Coordinate(startX, startY);

        st = new StringTokenizer(br.readLine());
        float endX = Float.parseFloat(st.nextToken());
        float endY = Float.parseFloat(st.nextToken());
        Coordinate end = new Coordinate(endX, endY);

        int n = Integer.parseInt(br.readLine());
        coordinates = new Coordinate[n + 2];
        adj = new ArrayList[n + 2];
        for (int i = 0; i < n + 2; i++) {
            adj[i] = new ArrayList<>();
        }

        // 시작점과 목적지 좌표 설정
        coordinates[0] = start;
        coordinates[n + 1] = end;

        // 대포들의 좌표 설정
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            float x = Float.parseFloat(st.nextToken());
            float y = Float.parseFloat(st.nextToken());
            coordinates[i] = new Coordinate(x, y);
        }

        // 그래프 구성
        // 시작점에서 대포들과 목적지까지의 초기 거리 계산
        for (int i = 1; i <= n + 1; i++) {
            float dist = distance(coordinates[0], coordinates[i]);
            adj[0].add(new Edge(i, dist / 5.0f));
        }

        // 대포들 사이의 최단 거리 계산
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n + 1; j++) {
                if (i != j) {
                    float dist = distance(coordinates[i], coordinates[j]);
                    float time = Math.min(dist / 5.0f, (Math.abs(dist - 50.0f)) / 5.0f + 2.0f);
                    adj[i].add(new Edge(j, time));
                }
            }
        }

        minTime = new float[n + 2];
        Arrays.fill(minTime, Float.MAX_VALUE);
        dijkstra();

        // 목적지까지의 최단 시간 출력
        System.out.println(minTime[n + 1]);
    }

    static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int start = 0;
        pq.offer(new Edge(start, 0));
        minTime[start] = 0;

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int currentVertex = current.next;
            float currentTime = current.time;

            if (currentTime > minTime[currentVertex]) continue;

            for (Edge next : adj[currentVertex]) {
                int nextVertex = next.next;
                float nextTime = currentTime + next.time;

                if (nextTime < minTime[nextVertex]) {
                    minTime[nextVertex] = nextTime;
                    pq.offer(new Edge(nextVertex, nextTime));
                }
            }
        }
    }

    static float distance(Coordinate p1, Coordinate p2) {
        return (float) Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }
}
