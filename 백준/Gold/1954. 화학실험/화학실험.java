import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 시약의 종류 n
        int n = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];

        // ai와 bi를 입력 받기
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            b[i] = sc.nextInt();
        }

        // 용액의 양 M
        int M = sc.nextInt();
        sc.close();

        System.out.println(findGasAmount(n, a, b, M));
    }

    public static int findGasAmount(int n, int[] a, int[] b, int M) {
        int maxB = 0;
        for (int bi : b) {
            if (bi > maxB) {
                maxB = bi;
            }
        }

        // y의 가능한 최소 값은 maxB 이상
        // y의 가능한 최대 값은 a[0] * (M // a[0]) + b[0] 이상임
        // 이보다 높은 값을 시도할 필요가 없음
        for (int y = maxB; y <= a[0] * (M / a[0]) + b[0]; y++) {
            int totalSolution = 0;
            boolean isValid = true;

            for (int i = 0; i < n; i++) {
                if ((y - b[i]) % a[i] != 0) {
                    isValid = false;
                    break;
                }

                int x_i = (y - b[i]) / a[i];
                if (x_i < 0 || x_i > M) {
                    isValid = false;
                    break;
                }

                totalSolution += x_i;
            }

            if (isValid && totalSolution == M) {
                return y;
            }
        }

        return 0;
    }
}
