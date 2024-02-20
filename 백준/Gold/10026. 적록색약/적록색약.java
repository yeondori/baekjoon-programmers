import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int N;
	static char[][] painting;
	static boolean[][] visited;
	static int answer1, answer2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		painting = new char[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			painting[i] = br.readLine().toCharArray();
		}
		
		getAnswer();
	}

	private static void getAnswer() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					search(i, j, painting[i][j]);
					answer1++;
				}
			}
		}

		// 적록색약
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], false);
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					search(i, j, painting[i][j]);
					answer2++;
				}
			}
		}
		
		System.out.println(answer1 + " " + answer2);
	}

	private static void search(int x, int y, char target) {
		if (x < 0 || x >= N || y < 0 || y >= N) {
			return;
		}
		
		if (visited[x][y] || painting[x][y] != target) {
			return;
		}
		
		visited[x][y] = true;
		
		// 적록색약
		if (painting[x][y] == 'G') {
			painting[x][y] = 'R';
		}
		
		search(x-1, y, target);
		search(x, y-1, target);
		search(x+1, y, target);
		search(x, y+1, target);
		
		return;
	}
}
