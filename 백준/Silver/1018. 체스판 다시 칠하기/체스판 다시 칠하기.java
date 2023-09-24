import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        final int size = 8;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer NM = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(NM.nextToken());
        int M = Integer.parseInt(NM.nextToken());

        Boolean[][] chessboard = new Boolean[N][M];
        for (int i=0; i<N; i++) {
            String[] str = br.readLine().split("");
            for (int j=0; j<M; j++) chessboard[i][j] = str[j].equals("W");
        }


        int result = N*M;
        for (int i=0; i<=N-size; i++) {
            for (int j=0; j<=M-size; j++) {
                int min = findMinNum(chessboard, size, i, j);
                if (min<result) result = min;
            }
        }
        System.out.println(result);
    }


    static int findMinNum(Boolean[][] booleanArr, int size, int row, int col) {

        int whiteFirst = 0;
        int blackFirst = 0;
        Boolean square = true;

        for (int i=row; i<row+size; i++) {
            for (int j=col; j<col+size; j++) {
                if (booleanArr[i][j] == square) blackFirst++;
                else whiteFirst++;
                square = !square;
            }
            square = !square;
        }

        return Math.min(whiteFirst,blackFirst);
    }
}