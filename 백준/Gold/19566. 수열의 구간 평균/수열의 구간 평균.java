import java.io.*;
import java.util.*;

// https://welog.tistory.com/430
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long K = Long.parseLong(st.nextToken());

        long[] A = new long[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Long.parseLong(st.nextToken());
        }

        // 누적합 계산
        long[] prefixSum = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            prefixSum[i] = prefixSum[i - 1] + A[i];
        }

        // 평균 K를 구하기 위한 수
        long target = K;

        // 맵을 이용한 구간의 평균이 K인 개수 세기
        Map<Long, Integer> countMap = new HashMap<>();
        countMap.put(0L, 1);  // 누적합이 0인 경우도 고려
        long count = 0;

        for (int i = 1; i <= N; i++) {
            long currentSum = prefixSum[i];
            long neededSum = currentSum - target * i;

            if (countMap.containsKey(neededSum)) {
                count += countMap.get(neededSum);
            }

            // 현재 누적합을 기반으로 맵 업데이트
            countMap.put(currentSum - target * i, countMap.getOrDefault(currentSum - target * i, 0) + 1);
        }

        System.out.println(count);
    }
}
