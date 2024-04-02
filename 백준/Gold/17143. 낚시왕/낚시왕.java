import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R, C, M, totalShark;
	static int[] dx = {0, -1, 1, 0, 0}; // 상하우좌
	static int[] dy = {0, 0, 0, 1, -1};

	static class Shark {
		int x, y, speed, dir, size;

		public Shark(int x, int y, int speed, int dir, int size) {
			this.x = x;
			this.y = y;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
	}

	static Queue<Shark> sharks;
	static Shark[][] board;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		sharks = new LinkedList<>();
		board = new Shark[R][C];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int speed = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());

			if (dir <= 2) { // 상하
				speed %= (R - 1) * 2;
			} else { // 좌우
				speed %= (C - 1) * 2;
			}
			board[x][y] = new Shark(x, y, speed, dir, size);
		}
		simulation();
		System.out.println(totalShark);
	}

	private static void simulation() {
		totalShark = 0;
		for (int fisherManPos = 0; fisherManPos < C; fisherManPos++) {
			fishing(fisherManPos);
			sharks = getSharks();
			moveShark();
		}
	}

	private static void moveShark() {
		board = new Shark[R][C];
		while (!sharks.isEmpty()) {
			Shark curShark = sharks.poll();
			int speed = curShark.speed;

			for(int s = 0; s < speed; s++) {
				int nx = curShark.x + dx[curShark.dir];
				int ny = curShark.y + dy[curShark.dir];

				if(nx < 0 || nx >= R || ny < 0 || ny >= C) {
					curShark.x -= dx[curShark.dir];
					curShark.y -= dy[curShark.dir];
					curShark.dir = changeDir(curShark.dir);
					continue;
				}
				
				curShark.x = nx;
				curShark.y = ny;
			}

			if (board[curShark.x][curShark.y] == null || board[curShark.x][curShark.y].size < curShark.size) {
				board[curShark.x][curShark.y] = curShark;
			}
		}
	}

	private static Queue<Shark> getSharks() {
		sharks = new LinkedList<>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (board[i][j] != null) {
					sharks.add(board[i][j]);
				}
			}
		}
		return sharks;
	}

	private static int changeDir(int dir) {
		if (dir == 1) return 2;
		else if (dir == 2) return 1;
		else if (dir == 3) return 4;
		else return 3;
	}

	private static void fishing(int pos) {
		for (int row = 0; row < R; row++) {
			if (board[row][pos] != null) {
				totalShark += board[row][pos].size;
				board[row][pos] = null;
				return;
			}
		}
	}
}
