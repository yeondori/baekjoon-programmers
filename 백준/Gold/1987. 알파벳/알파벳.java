import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int R, C;
	static char[][] board;

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int answer, value;
	static List<Character> alpha;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		board = new char[R][C];
		for (int i = 0; i < R; i++) {
			board[i] = br.readLine().toCharArray();
		}

		getAnswer();
	}

	private static void getAnswer() {

		alpha = new ArrayList<>();
		alpha.add(board[0][0]);
		search(0, 0);
		answer = Math.max(answer, value);

		System.out.println(answer);
	}

	private static void search(int x, int y) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
				continue;
			}
			
			if (alpha.contains(board[nx][ny])) {
				continue;
			}
			alpha.add(board[nx][ny]);
			search(nx, ny);
			int idx = alpha.size() - 1;
			alpha.remove(idx);
		}
		value = Math.max(value, alpha.size());
	}
}
