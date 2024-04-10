import java.util.*;

class Solution {
    final static int BLANK = 0, BLOCK = 1;
    
    static int[][] GAME_BOARD, TABLE;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class Point implements Comparable<Point> {
        int x, y;
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public int compareTo(Point o) {
            int compareRow = this.x-o.x;
            int compareCol = this.y-o.y;
            return compareRow == 0? compareCol : compareRow;
        }
    }
    static List<List<Point>> blocks, blanks;
    static int answer;
    
    public int solution(int[][] game_board, int[][] table) {
        // 0. 초기화
        blocks = new ArrayList<>();
        blanks = new ArrayList<>();
        GAME_BOARD = game_board;
        TABLE = table;
        
        // 1. 빈칸/블럭을 찾는다
        int boardRSize = GAME_BOARD.length;
        int boardCSize = GAME_BOARD[0].length;
        int tableRSize = TABLE.length;
        int tableCSize = TABLE[0].length;
        
        findBlocks(GAME_BOARD, BLANK, boardRSize, boardCSize);
        findBlocks(TABLE, BLOCK, tableRSize, tableCSize);
        
        // 2. 블럭 좌표 보정
        for(List<Point> bls : blocks) {
            modifyXY(bls);
        }
        for(List<Point> bls : blanks) {
            modifyXY(bls);
        }

        // 3. 블럭 비교
        compareBlocks();
        
        return answer;
    }
    
    private static void compareBlocks() {
        for (List<Point> block : blocks) {
            nextBlock:
            for (List<Point> blank : blanks) {
                // 사이즈가 다르면 비교할 필요가 없음
                if (block.size() != blank.size()) {
                    continue;
                }

                // 총 4번 회전하며 비교
                int size = block.size();
                for(int r = 0; r<4; r++) {
                    int corrCnt = 0;
                    for (int idx = 0; idx < size; idx++) {
                        Point bk = block.get(idx);
                        Point bl = blank.get(idx);

                        if (bk.x!=bl.x || bk.y!=bl.y) {
                            break;
                        }
                        corrCnt ++;
                    }
                    if (corrCnt==size) {
                        answer += size;
                        blanks.remove(blank);
                        break nextBlock; 
                    }
                    rotate(block);
                }
            }
        }
    }
    
    private static void rotate(List<Point> block) {
        int maxX = 0;
        for (Point p : block) {
            maxX = Math.max(maxX, p.x);
        }
        
        for (int i = 0, size = block.size(); i < size; i++) {
            Point p = block.get(i);
            int newX = p.y;
            int newY = maxX - p.x;
            p.x = newX;
            p.y = newY;
        }
        
        // 회전 후 다시 정렬
        Collections.sort(block);
    }
    
    private static void modifyXY(List<Point> block) {
        // 최소 행, 최소 열 찾기
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        
        for (Point p : block) {
            if (minX > p.x) {
                minX = p.x;
            }
            if (minY > p.y) {
                minY = p.y;
            }
        }
        
        // 최소 행, 열로 좌표 보정
        for (Point p : block) {
            p.x -= minX;
            p.y -= minY;
        }
        
        // 좌표순 정렬
        Collections.sort(block);
    }
    
    private static void findBlocks(int[][] board, int sep, int endX, int endY) {
        boolean[][] visited = new boolean[endX][endY];
        for (int i = 0; i < endX; i++) {
            for (int j = 0; j < endY; j++) {
                if (board[i][j] == sep && !visited[i][j]) {
                     bfs(sep, i, j, endX, endY, visited);
                } 
            }
        }
    }
    
    private static void bfs(int sep, int x, int y, int rSize, int cSize, boolean[][] visited) {
        int[][] board = new int[rSize][cSize];
        if (sep == BLANK) {
            board = GAME_BOARD;
        } else if (sep == BLOCK) {
            board = TABLE;
        }
        
        Queue<Point> q = new ArrayDeque<>();
        List<Point> curBlock = new ArrayList<>();
        
        q.add(new Point(x, y));
        visited[x][y] = true;
        
        while(!q.isEmpty()) {
            Point cur = q.poll();
            curBlock.add(cur);
            
            for(int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                
                if (nx<0 || nx >= rSize || ny < 0 || ny >= cSize) {
                    continue;
                }
                
                if (visited[nx][ny] || board[nx][ny] != sep) {
                    continue;
                }

                visited[nx][ny] = true;
                q.add(new Point(nx, ny));
            }
        }
        
        if (sep == BLANK) {
            blanks.add(curBlock);
        } else if (sep == BLOCK) {
            blocks.add(curBlock);
        }
    }
}