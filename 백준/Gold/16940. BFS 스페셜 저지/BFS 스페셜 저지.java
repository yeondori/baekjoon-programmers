import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        // 그래프를 인접 리스트로 표현
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }
        
        // 트리의 간선 정보 입력
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        
        // BFS 방문 순서 입력
        int[] bfsOrder = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            bfsOrder[i] = Integer.parseInt(st.nextToken());
        }
        
        // BFS 유효성 검증
        if (isValidBFS(graph, bfsOrder)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    private static boolean isValidBFS(List<List<Integer>> graph, int[] bfsOrder) {
        int N = bfsOrder.length;
        if (bfsOrder[0] != 1) return false; // 시작 정점이 1이 아니면 false
        
        // 방문 확인용 배열
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1] = true;
        
        int index = 1; // BFS 방문 순서의 현재 인덱스
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            List<Integer> neighbors = new ArrayList<>();
            
            for (int neighbor : graph.get(current)) {
                if (!visited[neighbor]) {
                    neighbors.add(neighbor);
                    visited[neighbor] = true;
                }
            }
            
            // 현재 노드의 모든 자식 노드가 올바른 순서로 큐에 들어가야 한다.
            Set<Integer> neighborSet = new HashSet<>(neighbors);
            int size = neighbors.size();
            for (int i = 0; i < size; i++) {
                if (index >= N || !neighborSet.contains(bfsOrder[index])) {
                    return false;
                }
                queue.add(bfsOrder[index]);
                neighborSet.remove(bfsOrder[index]);
                index++;
            }
        }
        
        return true;
    }
}
