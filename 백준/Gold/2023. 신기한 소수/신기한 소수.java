import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N, nextNum;
	static int[] numbers = {2, 3, 5, 7};
	static int[] addNumbers = {1, 3, 7, 9};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0, size = numbers.length; i < size; i++) {
			recursive(1,numbers[i]); 
		}

	}
	private static void recursive(int depth, int num) {
		if (depth == N) {
			if (isPrimeNumber(num)) {
				System.out.println(num);
			}
			return; 
		}
		
		for (int i=0, size=addNumbers.length; i<size; i++) {
			if (N != 1) {
				nextNum = num*10 + addNumbers[i];
			}
			
			if (isPrimeNumber(nextNum)) {
				recursive(depth+1,nextNum);
			}
		}
	}

	private static boolean isPrimeNumber(int num) {
		for (int i=2; i<=Math.sqrt(num); i++) {
			if (num%i == 0) {
				return false;
			}
		}
		return true;
	}
}
