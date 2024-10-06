import java.util.*;

class Solution {
    
    static String answer;
    static int row, col, targetX, targetY, targetCnt;
    
    static char[] dir = {'d', 'l', 'r', 'u'};
    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, -1, 1, 0};
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        row = n; col = m; targetX = r; targetY = c; targetCnt = k;
        
        int minDist = Math.abs(x - targetX) + Math.abs(y - targetY);
        
        if (minDist > k || (k - minDist) % 2 != 0) {
            return "impossible";
        }
        
        StringBuilder sb = new StringBuilder();
        solve(x, y, 0, sb);
        return answer == null ? "impossible" : answer; 
    }
    
    static boolean solve(int x, int y, int cnt, StringBuilder path) {
        if (cnt == targetCnt) {
            if (x == targetX && y == targetY) {
                answer = path.toString();
                return true;
            }
            return false;
        }
        
        int remainingMoves = targetCnt - cnt;
        int minDistToTarget = Math.abs(x - targetX) + Math.abs(y - targetY);
        
        if (minDistToTarget > remainingMoves || (remainingMoves - minDistToTarget) % 2 != 0) {
            return false;
        }
        
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            
            if (inRange(nx, ny)) {
                path.append(dir[d]);
                if (solve(nx, ny, cnt + 1, path)) {
                    return true;
                }
                path.deleteCharAt(path.length() - 1); 
            }
        }
        return false;
    }
    
    static boolean inRange(int x, int y) {
        return x >= 1 && x <= row && y >= 1 && y <= col;
    }
}
