import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://redbinalgorithm.tistory.com/625 참고
public class Main {

    static final char PLUS = '+', MINUS = '-', MULTI = '*';
    static int N;
    static int[][] max, min;
    static char[] input;
    static int[] calculateResults = new int[4]; // max-max, min-min, max-min, min-max의 4가지

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        input = br.readLine().toCharArray();

        getMaxResult();
    }

    private static void getMaxResult() {
        max = new int[N][N];
        min = new int[N][N];

        for (int i = 0; i < N; i+=2) {
            for (int j = 0; j < N; j+=2) {
                max[i][j] = Integer.MIN_VALUE;
                min[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < N; i += 2) {
            max[i][i] = min[i][i] = input[i] - '0';
        }

        for (int k = 2; k < N; k += 2) {
            for (int i = 0; i < N - k; i += 2) {
                for (int j = i + 1; j < i + k; j+=2) {
                    char cmd = input[j];
                    calculateResults[0] = calculate(cmd, max[i][j - 1], max[j + 1][i + k]);
                    calculateResults[1] = calculate(cmd, max[i][j - 1], min[j + 1][i + k]);
                    calculateResults[2] = calculate(cmd, min[i][j - 1], max[j + 1][i + k]);
                    calculateResults[3] = calculate(cmd, min[i][j - 1], min[j + 1][i + k]);

                    Arrays.sort(calculateResults);

                    max[i][i + k] = Math.max(max[i][i + k], calculateResults[3]);
                    min[i][i + k] = Math.min(min[i][i + k], calculateResults[0]);
                }
            }
        }

        System.out.println(max[0][N - 1]);
    }

//    private static int calculate(char operator, int num1, int num2) {
//        switch (operator) {
//            case PLUS -> {
//                return num1 + num2;
//            }
//
//            case MINUS -> {
//                return num1 - num2;
//            }
//
//            case MULTI -> {
//                return num1 * num2;
//            }
//        }
//        return 0;
//    }

    private static int calculate(char operator, int num1, int num2) {
        if (operator == PLUS) {
            return num1 + num2;
        } else if (operator == MINUS) {
            return num1 - num2;
        } else if (operator == MULTI) {
            return num1 * num2;
        }
        return 0;
    }
}
