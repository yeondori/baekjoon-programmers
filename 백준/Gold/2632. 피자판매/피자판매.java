// https://redbinalgorithm.tistory.com/636
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int m, n;
	static int a[];
	static int b[];
	static int sumA[] = new int[2000001];
	static int sumB[] = new int[2000001];

	public static void main(String[] args) throws NumberFormatException, IOException {
		init();
		System.out.println(Solve());
	}

	private static int Solve() {
		int answer = 0;
		funSumCnt(m, a, sumA);
		funSumCnt(n, b, sumB);
		for (int i = 0; i <= N; i++) {
			answer += sumA[i] * sumB[N - i];
		}

		return answer;
	}

	private static void funSumCnt(int c, int[] arr, int[] sumCnt) {
		for (int i = 0; i < c; i++) {
			int sum = 0;
			for (int j = i; j < i + c - 1; j++) {
				sum += arr[j];
				sumCnt[sum]++;
			}
		}
	}

	private static void init() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String[] str = br.readLine().split(" ");

		m = Integer.parseInt(str[0]);
		n = Integer.parseInt(str[1]);

		a = new int[m * 2];
		b = new int[n * 2];

		int sum = 0;
		for (int i = 0; i < m; i++) {
			a[i] = Integer.parseInt(br.readLine());
			a[i + m] = a[i];
			sum += a[i];
		}
		sumA[sum] = 1;
		sumA[0] = 1;

		sum = 0;
		for (int i = 0; i < n; i++) {
			b[i] = Integer.parseInt(br.readLine());
			b[i + n] = b[i];
			sum += b[i];
		}
		sumB[sum] = 1;
		sumB[0] = 1;
	}
}