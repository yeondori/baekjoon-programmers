import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    final static int SIZE = 9;
    static int[][] sudoku = new int[SIZE][SIZE];
    static int[] row = new int[SIZE], col = new int[SIZE], block = new int[SIZE];

    static class Pos {
        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static List<Pos> blanks;
    static int bSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        blanks = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            String input = br.readLine();
            for (int j = 0; j < SIZE; j++) {
                int num = input.charAt(j) - '0';
                sudoku[i][j] = num;
                if (num == 0) {
                    blanks.add(new Pos(i, j));
                } else {
                    row[i] |= (1 << num);
                    col[j] |= (1 << num);
                    int blockNumber = (i / 3) * 3 + (j / 3);
                    block[blockNumber] |= (1 << num);
                }
            }
        }
        bSize = blanks.size();
        fillSudoku(0);
    }

    private static void fillSudoku(int idx) {
        if (idx == bSize) {
            printSudoku();
            System.exit(0);
        }

        Pos p = blanks.get(idx);
        int x = p.x;
        int y = p.y;

        for (int num = 1; num <= SIZE; num++) {
            if (isValid(num, x, y)) {
                sudoku[x][y] = num;
                row[x] |= (1 << num);
                col[y] |= (1 << num);
                int blockNumber = (x / 3) * 3 + (y / 3);
                block[blockNumber] |= (1 << num);

                fillSudoku(idx + 1);

                sudoku[x][y] = 0;
                row[x] &= ~(1 << num);
                col[y] &= ~(1 << num);
                block[blockNumber] &= ~(1 << num);
            }
        }

    }

    private static boolean isValid(int num, int x, int y) {
        int blockNumber = (x / 3) * 3 + (y / 3);
        return ((row[x] & (1 << num)) == 0) && ((col[y] & (1 << num)) == 0) && ((block[blockNumber] & (1 << num)) == 0);
    }

    private static void printSudoku() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(sudoku[i][j]);
            }
            System.out.println();
        }
    }
}
