import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int PAPER_WIDTH = 100;
    static final int PAPER_LENGTH = 100;
    static final int COLORED_PAPER_WIDTH = 10;
    static final int COLORED_PAPER_LENGTH = 10;
    static int[][] paper = new int[PAPER_WIDTH][PAPER_LENGTH];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            fill(paper, x, y);
        }

        calculate();
    }

    private static void calculate() {
        int area = 0;
        for (int i = 0; i < PAPER_WIDTH; i++) {
            for (int j = 0; j < PAPER_LENGTH; j++) {
                if (paper[i][j] == 1) area += 1;
            }
        }
        System.out.println(area);
    }

    public static void fill(int[][] matrix, int x, int y) {
        for (int i = 0; i < COLORED_PAPER_WIDTH; i++) {
            for (int j = 0; j < COLORED_PAPER_LENGTH; j++) {
                matrix[x + i][y + j] = 1;
            }
        }
    }
}