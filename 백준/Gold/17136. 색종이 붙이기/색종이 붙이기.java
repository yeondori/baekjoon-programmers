import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	final static int BOARD_SIZE = 10;
	final static int MAX_PAPER_SIZE = 5;
	final static int[] paperNum = { 0, 5, 5, 5, 5, 5 };

	static int minPaper;
	static boolean clearFlag;
	static boolean[][] board = new boolean[BOARD_SIZE][BOARD_SIZE];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < BOARD_SIZE; i++) {
			String[] input = br.readLine().split(" ");
			for (int j = 0; j < BOARD_SIZE; j++) {
				board[i][j] = input[j].equals("1");
			}
		}

//		printBoard();
		minPaper = Integer.MAX_VALUE;
		findMinPaper(0, 0, 0);
		if (!clearFlag) {
			minPaper = -1;
		}
		System.out.println(minPaper);
	}

	private static void findMinPaper(int row, int col, int usePaperNum) {
		if (row >= BOARD_SIZE) {
			if(checkBoardClear() && minPaper > usePaperNum) {
				minPaper = usePaperNum;
			}
			return;
		}
		
		if (col >= BOARD_SIZE) {
			findMinPaper(row+1, 0, usePaperNum);
			return;
		}
		
	
		if (board[row][col]) {
			for (int paperSize = 1; paperSize <= MAX_PAPER_SIZE; paperSize++) {
				if (canStick(row, col, paperSize)) {
					changePaper(row, col, paperSize, false);
					paperNum[paperSize]--;
					findMinPaper(row, col+1, usePaperNum + 1);
					changePaper(row, col, paperSize, true);
					paperNum[paperSize]++;
				}
			}
		} else {
			findMinPaper(row, col+1, usePaperNum);
		}
	}

	private static boolean checkBoardClear() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				if (board[i][j]) {
					return false;
				}
			}
		}
		clearFlag = true;
		return true;
	}

	private static void changePaper(int row, int col, int paperSize, boolean value) {
		for (int i = row; i < row + paperSize; i++) {
			for (int j = col; j < col + paperSize; j++) {
				board[i][j] = value;
			}
		}
	}

	private static boolean canStick(int row, int col, int paperSize) {
		if (paperNum[paperSize] <= 0) {
			return false;
		}

		for (int i = row; i < row + paperSize; i++) {
			for (int j = col; j < col + paperSize; j++) {
				if (i >= BOARD_SIZE || j >= BOARD_SIZE) {
					return false;
				}

				if (!board[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
}
