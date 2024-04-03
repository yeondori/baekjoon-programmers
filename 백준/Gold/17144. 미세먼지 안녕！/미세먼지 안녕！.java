import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int boardRow, boardCol, totalTime, cleanerEnd;
	static int[][] board;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static class Dust {
		int x, y, volume;

		public Dust(int x, int y, int volume) {
			this.x = x;
			this.y = y;
			this.volume = volume;
		}
	}

	static Queue<Dust> dusts;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		boardRow = Integer.parseInt(st.nextToken());
		boardCol = Integer.parseInt(st.nextToken());
		totalTime = Integer.parseInt(st.nextToken());

		board = new int[boardRow][boardCol];
		for (int i = 0; i < boardRow; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < boardCol; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == -1) {
					cleanerEnd = i;
				}
			}
		}
		simulation();
	}

	private static void simulation() {
		for (int time = 0; time < totalTime; time++) {
			// 1. 미세먼지 확산
			spreadDust();
			// 2. 공기 청정기 작동
			airCleaning();
		}
		System.out.println(getTotalDustVolume());
	}

	private static void spreadDust() {
		getDust();
		int[][] newBoard = new int[boardRow][boardCol];

		while (!dusts.isEmpty()) {
			Dust curDust = dusts.poll();
			// 확산할 값이 0인 경우
			if (curDust.volume < 5) {
				newBoard[curDust.x][curDust.y] += curDust.volume;
				continue;
			}
			int cnt = 0;
			int spreadVolume = curDust.volume / 5;
			for (int i = 0; i < 4; i++) {
				int nx = curDust.x + dx[i];
				int ny = curDust.y + dy[i];

				if (nx < 0 || nx >= boardRow || ny < 0 || ny >= boardCol) {
					continue;
				}

				if (board[nx][ny] == -1) {
					continue;
				}
				cnt++;
				newBoard[nx][ny] += spreadVolume;
			}
			int updateVolume = curDust.volume - cnt * spreadVolume;
			newBoard[curDust.x][curDust.y] += updateVolume;
		}

		board = newBoard;
	}

	private static void getDust() {
		dusts = new LinkedList<>();
		for (int row = 0; row < boardRow; row++) {
			for (int col = 0; col < boardCol; col++) {
				if (board[row][col] > 0) {
					dusts.add(new Dust(row, col, board[row][col]));
				}
			}
		}
	}

	private static void airCleaning() {
		int top = cleanerEnd-1;
        int bottom = cleanerEnd;
        
        // 반시계방향
        for (int i = top - 1; i > 0; i--) {
            board[i][0] = board[i - 1][0];
        }
        for (int i = 0; i < boardCol - 1; i++) {
        	board[0][i] = board[0][i + 1];
        }
        for (int i = 0; i < top; i++) {
        	board[i][boardCol - 1] = board[i + 1][boardCol - 1];
        }
        for (int i = boardCol - 1; i > 1; i--) {
        	board[top][i] = board[top][i - 1];
        }
        board[top][1] = 0;

        // 시계방향
        for (int i = bottom + 1; i < boardRow - 1; i++) {
        	board[i][0] = board[i + 1][0];
        }
        for (int i = 0; i < boardCol - 1; i++) {
        	board[boardRow - 1][i] = board[boardRow - 1][i + 1];
        }
        for (int i = boardRow - 1; i > bottom; i--) {
        	board[i][boardCol - 1] = board[i - 1][boardCol - 1];
        }
        for (int i = boardCol - 1; i > 1; i--) {
        	board[bottom][i] = board[bottom][i - 1];
        }
        board[bottom][1] = 0;	
        
        board[top][0] = -1;
        board[bottom][0] = -1;
	}

	private static int getTotalDustVolume() {
		int totalDustVolume = 0;
		for (int row = 0; row < boardRow; row++) {
			for (int col = 0; col < boardCol; col++) {
				if (board[row][col] > 0) {
					totalDustVolume += board[row][col];
				}
			}
		}
		return totalDustVolume;
	}

//	private static void printBoard() {
//		for (int row = 0; row < boardRow; row++) {
//			for (int col = 0; col < boardCol; col++) {
//				System.out.print(board[row][col] + " ");
//			}
//			System.out.println();
//		}
//	}
}
