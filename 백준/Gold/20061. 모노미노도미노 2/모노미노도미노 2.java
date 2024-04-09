import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int BLOCK_ONE = 1, BLOCK_ONE_BY_TWO = 2, BLOCK_TWO_BY_ONE = 3;
    static final int SPECIAL_SIZE = 2, BOARD_SIZE = 4;
    static int[][] green, blue;
    static int blockNum, totalScore;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        green = new int[SPECIAL_SIZE + BOARD_SIZE][BOARD_SIZE];
        blue = new int[SPECIAL_SIZE + BOARD_SIZE][BOARD_SIZE];
        blockNum = Integer.parseInt(br.readLine().trim());
        for (int turn = 1; turn <= blockNum; turn++) {
            st = new StringTokenizer(br.readLine());

            int blockType = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            dropBlock(blockType, x, y);
        }
        printAnswer();
    }

    private static void printAnswer() {
        System.out.println(totalScore);

        int totalBlockNum = 0;
        for (int i = 0; i < BOARD_SIZE + SPECIAL_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (green[i][j] == 1) {
                    totalBlockNum++;
                }
                if (blue[i][j] == 1) {
                    totalBlockNum++;
                }
            }
        }
        System.out.println(totalBlockNum);
    }

    private static void dropBlock(int blockType, int x, int y) {
        //  1. 블럭을 놓을 위치를 찾는다.
        int fromX = x, toX = x;
        int fromY = y, toY = y;

        // 1-1. 초록색
        if (blockType == BLOCK_ONE) {
            fromX = toX = 0;
            fromY = toY = y;
        } else if (blockType == BLOCK_ONE_BY_TWO) {
            fromX = toX = 0;
            fromY = y;
            toY = fromY + 1;
        } else if (blockType == BLOCK_TWO_BY_ONE) {
            fromX = 0;
            toX = fromX + 1;
            fromY = y;
            toY = y;
        }
        findRegion(green, fromX, toX, fromY, toY);

        // 1-2. 파란색
        if (blockType == BLOCK_ONE) {
            fromX = toX = 0;
            fromY = toY = 3-x;
        } else if (blockType == BLOCK_ONE_BY_TWO) {
            fromX = 0;
            toX = fromX + 1;
            fromY = 3-x;
            toY = 3-x;
        } else if (blockType == BLOCK_TWO_BY_ONE) {
            fromX = 0;
            toX = 0;
            toY = 3-x;
            fromY = toY-1;
        }
        findRegion(blue, fromX, toX, fromY, toY);
        
        // 2. 가득 차있는 행이 없을 때까지 해당 행을 삭제하고 블럭을 이동한다.
        clearRow(green);
        clearRow(blue);
        // 3. 특별 구역에 블럭이 있으면 개수만큼 마지막 행을 삭제하고 블럭을 이동한다.
        checkSpecialRow(green);
        checkSpecialRow(blue);
    }

    private static void checkSpecialRow(int[][] board) {
        int cnt = 0;
        for (int row = 0; row < SPECIAL_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] == 1) {
                    cnt++;
                    break;
                }
            }
        }

        int length = board.length;
        for (int c = 0; c < cnt; c++) {
            for (int i = length - 1; i > 0; i--) {
                board[i] = board[i - 1].clone();
            }
            Arrays.fill(board[0], 0);
        }
    }

    private static void clearRow(int[][] board) {
        for (int row = 0, rSize = board.length; row < rSize; row++) {
            int cnt = 0;
            for (int col = 0, cSize = board[0].length; col < cSize; col++) {
                if (board[row][col] == 1) {
                    cnt++;
                }
            }

            if (cnt == BOARD_SIZE) {
                totalScore += 1;
                Arrays.fill(board[row], 0);

                for (int i = row; i > 0; i--) {
                    board[i] = board[i - 1].clone();
                }
                Arrays.fill(board[0], 0);
                clearRow(board);
            }
        }
    }

    private static void findRegion(int[][] board, int fromX, int toX, int fromY, int toY) {
        while (isInbound(fromX, fromY) && isInbound(toX, toY) && board[fromX][fromY] == 0 && board[toX][toY] == 0) {
            fromX++;
            toX++;
        }

        fromX--;
        toX--;

        board[fromX][fromY] = 1;
        board[toX][toY] = 1;
    }

    private static boolean isInbound(int x, int y) {
        return x >= 0 && x < BOARD_SIZE + SPECIAL_SIZE && y >= 0 && y < BOARD_SIZE;
    }
}