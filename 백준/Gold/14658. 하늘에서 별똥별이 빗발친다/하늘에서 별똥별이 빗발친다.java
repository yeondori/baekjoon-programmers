import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 구역의 가로길이
        int M = sc.nextInt(); // 구역의 세로길이
        int L = sc.nextInt(); // 트램펄린의 한 변의 길이
        int K = sc.nextInt(); // 별똥별의 수

        int[][] meteors = new int[K][2]; // 별똥별의 위치를 저장할 배열

        for (int i = 0; i < K; i++) {
            meteors[i][0] = sc.nextInt();
            meteors[i][1] = sc.nextInt();
        }

        int minHit = K; // 지구에 부딪히는 별똥별의 최소 개수

        // 트램펄린의 모든 가능한 배치를 탐색
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < K; j++) {
                int x1 = meteors[i][0];
                int y1 = meteors[j][1];
                int x2 = x1 + L;
                int y2 = y1 + L;

                if (x2 > N) x1 = N - L;
                if (y2 > M) y1 = M - L;

                int count = 0; // 트램펄린에 포함되는 별똥별의 개수
                for (int k = 0; k < K; k++) {
                    int x = meteors[k][0];
                    int y = meteors[k][1];
                    if (x1 <= x && x <= x2 && y1 <= y && y <= y2) {
                        count++;
                    }
                }

                // 트램펄린에 포함되지 않는 별똥별의 개수
                int hit = K - count;
                minHit = Math.min(minHit, hit);
            }
        }

        System.out.println(minHit);
        sc.close();
    }
}
