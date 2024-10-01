import java.util.*;

class Solution {
    
    public int solution(int n, int[][] edge) {
        
        List<Integer>[] graph = new ArrayList[n+1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i = 0, len = edge.length; i < len; i++) {
            int from = edge[i][0];
            int to = edge[i][1];
            graph[from].add(to);
            graph[to].add(from);
        }
        
        int[] dist = new int[n+1];
        Arrays.fill(dist, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        dist[1] = 0; 

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int next : graph[current]) {
                if (dist[next] == -1) { 
                    dist[next] = dist[current] + 1; 
                    queue.offer(next);
                }
            }
        }

        int maxDist = 0;
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] > maxDist) {
                maxDist = dist[i];
                count = 1; 
            } else if (dist[i] == maxDist) {
                count++; 
            }
        }

        return count; 
    }
}