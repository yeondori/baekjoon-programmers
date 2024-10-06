import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
//https://koguri.tistory.com/56
    static int[] board = {
            0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 0,  // 0 ~ 21
            10, 13, 16, 19, 25, 30, 35, 40, 0,  // 22 ~ 30
            20, 22, 24, 25, 30, 35, 40, 0,      // 31 ~ 38
            30, 28, 27, 26, 25, 30, 35, 40, 0   // 39 ~ 47
    };
    static int[] dice = new int[10];       
    static int[] pieceOrders = new int[10]; 
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 10; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }

        permutation(0);  
        System.out.println(answer); 
    }

    public static void permutation(int cnt) {
        if (cnt == 10) {
            play(); 
            return;
        }

        for (int i = 0; i < 4; i++) { 
            pieceOrders[cnt] = i;
            permutation(cnt + 1);
        }
    }

    public static void play() {
        boolean[] visited = new boolean[board.length]; 
        int score = 0;
        int[] p = new int[4]; 

        for (int i = 0; i < 10; i++) {
            int nowDice = dice[i]; 
            int nowPiece = pieceOrders[i]; 

            if (isFinish(p[nowPiece])) return; 

            int next = nextPosition(p[nowPiece], nowDice);  

            if (isFinish(next)) { 
                visit(visited, p[nowPiece], false); 
                p[nowPiece] = next;  
                continue;
            }

            if (visited[next]) return; 

            visit(visited, p[nowPiece], false);  
            visit(visited, next, true);

            p[nowPiece] = next; 
            score += board[p[nowPiece]];  
        }

        answer = Math.max(answer, score); 
    }

    public static void visit(boolean[] visited, int idx, boolean check) {
        if (idx == 20 || idx == 29 || idx == 37 || idx == 46) {
            visited[20] = check;
            visited[29] = check;
            visited[37] = check;
            visited[46] = check;
        } else if (idx == 26 || idx == 34 || idx == 43) {
            visited[26] = check;
            visited[34] = check;
            visited[43] = check;
        } else if (idx == 27 || idx == 35 || idx == 44) {
            visited[27] = check;
            visited[35] = check;
            visited[44] = check;
        } else if (idx == 28 || idx == 36 || idx == 45) {
            visited[28] = check;
            visited[36] = check;
            visited[45] = check;
        } else {
            visited[idx] = check;
        }
    }

    public static int nextPosition(int nowIdx, int dice) {
        int nextIdx = nowIdx + dice;

        if (nowIdx < 21) {
            if (nextIdx >= 21) nextIdx = 21;
        } else if (nowIdx < 30) {
            if (nextIdx >= 30) nextIdx = 30;
        } else if (nowIdx < 38) {
            if (nextIdx >= 38) nextIdx = 38;
        } else if (nowIdx < 47) {
            if (nextIdx >= 47) nextIdx = 47;
        }

        if (nextIdx == 5) return 22;  
        if (nextIdx == 10) return 31; 
        if (nextIdx == 15) return 39; 

        return nextIdx;
    }

    public static boolean isFinish(int idx) {
        return idx == 21 || idx == 30 || idx == 38 || idx == 47; 
    }
}
