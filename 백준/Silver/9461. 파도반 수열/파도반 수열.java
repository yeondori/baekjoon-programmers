import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int T, N;
	static long[] sequence;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			sequence = new long[N+1];
			
				
			for (int i = 1; i <= 3; i++) {
				sequence[i] = 1;
				if (i==N) {
					break;
				}
			}

			for (int i=4; i <= N; i++) {
				sequence[i] = sequence[i-2] + sequence[i-3];
			}
			
			answer.append(sequence[N]).append("\n");
		}
		System.out.println(answer);
	}
}
