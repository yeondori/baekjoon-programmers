import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static final int T = 10;
    static int N, startNode;
    static Map<Integer, List<Integer>> edges;
    static StringBuilder answer;
    static int lastBigNode;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        answer = new StringBuilder("");
        for (int tc = 1; tc <= T; tc++) {
            answer.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            startNode = Integer.parseInt(st.nextToken());

            String[] input = br.readLine().split(" ");
            edges = new HashMap<>();
            for (int i = 0; i < N; i+=2) {
                int from = Integer.parseInt(input[i]);
                int to = Integer.parseInt(input[i+1]);

                edges.putIfAbsent(from, new ArrayList<>());
                edges.get(from).add(to);

                edges.putIfAbsent(to, new ArrayList<>());
            }
            bfs();
            answer.append(lastBigNode).append("\n");
        }
        System.out.println(answer);
    }

    private static void bfs() {
        Queue<Integer> contact = new LinkedList<>();
        boolean[] visited = new boolean[101];
        contact.add(startNode);
        visited[startNode] = true;

        ArrayList<Integer> lastNodes = new ArrayList<>();
        int depth = 0;
        while(!contact.isEmpty()) {
            lastNodes.clear();
            int adjNum = contact.size(); // 현재 depth의 노드 개수
            for (int i = 0; i < adjNum; i++) {
                int cur = contact.poll();
                lastNodes.add(cur);
                for (int j = 0, size = edges.get(cur).size(); j < size; j++) {
                    int adj = edges.get(cur).get(j);
                    if (visited[adj]) {
                        continue;
                    }
                    contact.add(adj);
                    visited[adj] = true;
                }
                depth++;
            }
        }

        Collections.sort(lastNodes);
        lastBigNode = lastNodes.get(lastNodes.size()-1);
    }
}
