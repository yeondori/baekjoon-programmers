import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // T 값 입력
        long T = scanner.nextLong();

        // 배열 A 입력
        int n = scanner.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }

        // 배열 B 입력
        int m = scanner.nextInt();
        int[] B = new int[m];
        for (int i = 0; i < m; i++) {
            B[i] = scanner.nextInt();
        }

        // 배열 A의 모든 부분합 계산
        List<Long> sumA = getSubarraySums(A);
        // 배열 B의 모든 부분합 계산
        List<Long> sumB = getSubarraySums(B);

        // 정렬
        Collections.sort(sumA);
        Collections.sort(sumB);

        // 투 포인터 사용
        long count = countPairsWithSum(sumA, sumB, T);

        // 결과 출력
        System.out.println(count);
    }

    // 부 배열의 모든 합을 계산하여 리스트로 반환하는 함수
    private static List<Long> getSubarraySums(int[] array) {
        List<Long> subarraySums = new ArrayList<>();
        int n = array.length;
        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = i; j < n; j++) {
                sum += array[j];
                subarraySums.add(sum);
            }
        }
        return subarraySums;
    }

    // 투 포인터를 이용하여 두 리스트의 합이 target인 쌍의 개수를 계산
    private static long countPairsWithSum(List<Long> sumA, List<Long> sumB, long target) {
        long count = 0;
        int left = 0;
        int right = sumB.size() - 1;

        while (left < sumA.size() && right >= 0) {
            long currentSum = sumA.get(left) + sumB.get(right);

            if (currentSum == target) {
                // 중복된 값의 개수를 세기 위해 동일한 값이 있는 범위를 모두 처리
                long leftValue = sumA.get(left);
                long rightValue = sumB.get(right);
                long leftCount = 0;
                long rightCount = 0;

                // 같은 값을 가지는 A 부분합의 개수를 셈
                while (left < sumA.size() && sumA.get(left) == leftValue) {
                    leftCount++;
                    left++;
                }

                // 같은 값을 가지는 B 부분합의 개수를 셈
                while (right >= 0 && sumB.get(right) == rightValue) {
                    rightCount++;
                    right--;
                }

                count += leftCount * rightCount;
            } else if (currentSum < target) {
                left++;
            } else {
                right--;
            }
        }

        return count;
    }
}
