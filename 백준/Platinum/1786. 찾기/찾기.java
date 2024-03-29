import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		String pattern = br.readLine();

		int[] pi = getPI(pattern);
		int in = input.length();
		int pn = pi.length;
		StringBuilder ans = new StringBuilder();
		int cnt = 0;
		int j = 0;
		for (int i = 0; i < in; i++) {
			while (j > 0 && input.charAt(i) != pattern.charAt(j)) {
				j = pi[j - 1];
			}
			if (input.charAt(i) == pattern.charAt(j)) {
				if (j == pn - 1) {
					cnt++;
					ans.append((i - pn + 2)).append(" ");
					j = pi[j];
				} else {
					j++;
				}
			}
		}

		System.out.println(cnt);
		System.out.println(ans);
	}

	private static int[] getPI(String pattern) {
		int n = pattern.length();
		int[] pi = new int[n];
		int j = 0;
		for (int i = 1; i < n; i++) {
			while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
				j = pi[j - 1];
			}

			if (pattern.charAt(i) == pattern.charAt(j)) {
				pi[i] = ++j;
			}
		}
		return pi;
	}
}
