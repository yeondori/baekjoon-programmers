import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	final static int K = 1_000_000;
	static long N;
	
	static int[] pibo;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Long.parseLong(br.readLine().trim());
		int P = 15 * K/10;
		pibo = new int[P];
		
		pibo[0] = 0 % K;
		pibo[1] = 1 % K;
		for (int i = 2; i < P; i++) {
			pibo[i] = pibo[i-1] + pibo[i-2];
			pibo[i] %= K;
			if (i==N) {
				System.out.println(pibo[i]);
				return;
			}
		}
		
		long modN = N % (long)P;
		int idx = (int) modN;
		System.out.println(pibo[idx]);
	}
}
