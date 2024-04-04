import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	final static boolean A = true, B = false;

	static int testCaseNum, filmWidth, filmDepth, passStandard, minInjection;
	static boolean[][] film;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder answer = new StringBuilder();
		testCaseNum = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= testCaseNum; tc++) {
			st = new StringTokenizer(br.readLine());
			filmDepth = Integer.parseInt(st.nextToken());
			filmWidth = Integer.parseInt(st.nextToken());
			passStandard = Integer.parseInt(st.nextToken());

			film = new boolean[filmDepth][filmWidth];
			for (int row = 0; row < filmDepth; row++) {
				st = new StringTokenizer(br.readLine());
				for (int col = 0; col < filmWidth; col++) {
					film[row][col] = st.nextToken().equals("0");
				}
			}
			minInjection = filmDepth;
			injection(0, 0);
			answer.append("#").append(tc).append(" ").append(minInjection).append("\n");
		}
		System.out.println(answer);
	}

	private static void injection(int depth, int injectCnt) {
        if (isPass()) {
			if (minInjection > injectCnt) {
				minInjection = injectCnt;
			}
			return;
		}
        
        if (injectCnt >= minInjection) {
			return;
		}
		
		if (depth == filmDepth) {
			return;
		}
        
		injection(depth + 1, injectCnt);
		boolean[] temp = film[depth].clone();
        
		fill(depth, A);
		injection(depth + 1, injectCnt + 1);

		fill(depth, B);
		injection(depth + 1, injectCnt + 1);
        
		film[depth] = temp;
	}

	private static void fill(int row, boolean value) {
		for (int col = 0; col < filmWidth; col++) {
			film[row][col] = value;
		}
	}
	
	private static boolean isPass() {
		for (int col = 0; col < filmWidth; col++) {
			int curColCnt = 0;
			boolean target = film[0][col];
			for (int row = 0; row < filmDepth; row++) {
				if (target == film[row][col]) {
					curColCnt++;
				} else {
					target = film[row][col];
					curColCnt = 1;
				}
				if (curColCnt == passStandard) {
					break;
				}
			}
			if (curColCnt < passStandard) {
				return false;
			}
		}
		return true;
	}
}
