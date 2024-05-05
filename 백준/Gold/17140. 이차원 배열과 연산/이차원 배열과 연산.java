import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int MAX_SIZE = 100, START_SIZE = 3;
    static int TargetRow, TargetCol, TargetValue, row, col;
    static int[][] board;
    static Map<Integer, Integer> lineFreq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        TargetRow = Integer.parseInt(st.nextToken()) - 1;
        TargetCol = Integer.parseInt(st.nextToken()) - 1;
        TargetValue = Integer.parseInt(st.nextToken());

        board = new int[MAX_SIZE][MAX_SIZE];
        for (int i = 0; i < START_SIZE; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < START_SIZE; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();
    }

    private static void solve() {
        int t = 0;
        row = START_SIZE;
        col = START_SIZE;
        lineFreq = new HashMap<>();
        while (board[TargetRow][TargetCol] != TargetValue) {
            if (row >= col) {
                // 행 정렬
                sortRow();
            } else {
                // 열 정렬
                sortCol();
            }
            t++;

            if (t > 100) {
                t = -1;
                break;
            }
        }
        System.out.println(t);
    }

    private static void sortRow() {
        int maxCol = 0;
        for (int r = 0; r < row; r++) {
            int nCol = 0;
            lineFreq.clear();
            for (int c = 0; c < col; c++) {
                if (board[r][c] == 0) continue;
                lineFreq.put(board[r][c], lineFreq.getOrDefault(board[r][c], 0) + 1);
            }

            nCol = lineFreq.size() * 2;
            if (nCol > maxCol) {
                maxCol = nCol;
            }

            List<Integer> keySet = new ArrayList<>(lineFreq.keySet());
            keySet.sort((o1, o2) -> {
                int freqDiff = lineFreq.get(o1) - lineFreq.get(o2);
                if (freqDiff == 0) {
                    return o1 - o2;
                }
                return freqDiff;
            });

            int idx = 0;
            for (int key : keySet) {
                board[r][idx++] = key;
                board[r][idx++] = lineFreq.get(key);
            }
            for (int c = nCol; c < col; c++) {
                board[r][c] = 0;
            }
        }
        col = maxCol;
    }

    private static void sortCol() {
        int maxRow = 0;
        for (int c = 0; c < col; c++) {
            int nRow = 0;
            lineFreq.clear();
            for (int r = 0; r < row; r++) {
                if (board[r][c] == 0) continue;
                lineFreq.put(board[r][c], lineFreq.getOrDefault(board[r][c], 0) + 1);
            }

            nRow = lineFreq.size() * 2;
            if (nRow > maxRow) {
                maxRow = nRow;
            }

            List<Integer> keySet = new ArrayList<>(lineFreq.keySet());
            keySet.sort((o1, o2) -> {
                int freqDiff = lineFreq.get(o1) - lineFreq.get(o2);
                if (freqDiff == 0) {
                    return o1 - o2;
                }
                return freqDiff;
            });

            int idx = 0;
            for (int key : keySet) {
                board[idx++][c] = key;
                board[idx++][c] = lineFreq.get(key);
            }
            for (int r = nRow; r < row; r++) {
                board[r][c] = 0;
            }
        }
        row = maxRow;
    }
}
