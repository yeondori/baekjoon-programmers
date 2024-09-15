import java.util.Scanner;

public class Main {
    
    // 주어진 수 n까지의 1의 개수를 세는 함수
    public static long countOnes(long n) {
        long count = 0;
        long powerOfTwo = 1;
        
        while (powerOfTwo <= n) {
            long fullGroups = (n + 1) / (powerOfTwo * 2);
            long remainingBits = (n + 1) % (powerOfTwo * 2);
            
            // 각 자리에 대해 full group에서 1의 개수
            count += fullGroups * powerOfTwo;
            // 나머지 비트 중에서 1의 개수가 추가로 있는지 확인
            if (remainingBits > powerOfTwo) {
                count += remainingBits - powerOfTwo;
            }
            
            powerOfTwo *= 2;
        }
        
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        long A = sc.nextLong();
        long B = sc.nextLong();
        
        // B까지의 1의 개수 - (A-1)까지의 1의 개수
        long result = countOnes(B) - countOnes(A - 1);
        
        System.out.println(result);
        
        sc.close();
    }
}
