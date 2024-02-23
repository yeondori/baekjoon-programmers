import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    static int N, d, k, c;
    static int maxSushi;
    static int[] sushiBelt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        d = Integer.parseInt(input[1]);
        k = Integer.parseInt(input[2]);
        c = Integer.parseInt(input[3]);

        int rotate = 0; // 순환하므로 0부터 k까지의 값을 배열에 덧붙임
        sushiBelt = new int[N + k];
        for (int i = 0; i < N + k; i++) {
            if (i >= N) {
                sushiBelt[i] = sushiBelt[rotate++];
                continue;
            }
            int sushi = Integer.parseInt(br.readLine());
            sushiBelt[i] = sushi;
        }
        eatSushi();
        System.out.println(maxSushi);
    }

    private static void eatSushi() {
        Deque<Integer> eat = new ArrayDeque<>(k);
        int[] dishes = new int[d + 1];

        int isCouponSushi = 0;
        // 초기화
        int dishNum = 0;
        for (int i = 0; i < k; i++) {
            int sushi = sushiBelt[i];
            eat.add(sushi);
            dishes[sushi]++;
            if (dishes[sushi] == 1) {
                dishNum++;
            }
        }
        if (dishes[c] == 0) {
            isCouponSushi = 1;
        }
        maxSushi = Math.max(maxSushi, dishNum + isCouponSushi);

        for (int i = 0; i < N - 1; i++) {
            isCouponSushi = 0;
            // 첫번째 초밥 삭제
            int removedSushi = eat.pollFirst();
            dishes[removedSushi]--;
            if (dishes[removedSushi] == 0) dishNum--;
            // 새 초밥 추가
            int sushi = sushiBelt[i + k];
            eat.addLast(sushi);
            dishes[sushi]++;
            if (dishes[sushi] == 1) {
                dishNum++;
            }
            if (dishes[c] == 0) {
                isCouponSushi = 1;
            }
            maxSushi = Math.max(maxSushi, dishNum + isCouponSushi);
        }
    }
}
