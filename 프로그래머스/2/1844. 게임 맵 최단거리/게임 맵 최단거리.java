import java.util.LinkedList;
import java.util.Queue;

class Solution {
    
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();
        
        queue.add(new int[] {0, 0, 1});
        visited[0][0] = true;
        
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int row = now[0];
            int col = now[1];
            int dist = now[2];
            
            if (row == n - 1 && col == m - 1) {
                return dist;
            }
            
            for (int dir = 0; dir < 4; dir++) {
                int nx = row + dx[dir];
                int ny = col + dy[dir];
                
                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if (!visited[nx][ny] && maps[nx][ny] == 1) {
                        visited[nx][ny] = true;
                        queue.add(new int[] {nx, ny, dist + 1});
                    }
                }
            }
        }
        
        return -1;
    }
}
