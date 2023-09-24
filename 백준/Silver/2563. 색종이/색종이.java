import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static final int paperWidth = 100;
    static final int paperLength = 100;
    static final int coloredPaperWidth = 10;
    static final int coloredPaperLength = 10;

    public static void main(String[] args) throws IOException {

        int[][] paper = new int [paperWidth][paperLength];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i=0; i<N; i++) {
            StringTokenizer xy = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(xy.nextToken())-1;
            int y = Integer.parseInt(xy.nextToken())-1;
            paper = fill(paper, x, y);
        }

        int area = 0;
        for (int i=0; i<paperWidth; i++) {
            for (int j=0; j<paperLength; j++) {
                if (paper[i][j] == 1) area +=1;
            }
        }

        System.out.println(area);
    }

    public static int[][] fill(int[][] matrix, int x, int y) {
        for (int i=0; i<coloredPaperWidth; i++) {
            for (int j=0; j<coloredPaperLength; j++) {
                matrix[x+i][y+j] = 1;
            }
        }
        return matrix;
    }
}