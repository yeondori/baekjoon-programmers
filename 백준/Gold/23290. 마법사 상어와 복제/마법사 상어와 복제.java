import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static final int BOARD_SIZE = 4, SHARK_MOVE = 3,
            NONE = 0, SMELL_1 = 2, SMELL_2 = 3;

    static int sharkX, sharkY, maxFishCnt;
    static int[] tempSharkMoves, sharkMoves;

    static boolean[][] visited;

    static int[] sx = {-1, 0, 1, 0};
    static int[] sy = {0, -1, 0, 1};

    static char[] dir = {'←', '↖', '↑', '↗', '→', '↘', '↓', '↙'};

    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    static class Cell {
        List<Integer> fishes;
        int status;
        boolean isShark;

        Cell() {
            fishes = new ArrayList<>();
            status = NONE;
            isShark = false;
        }
    }

    static class Fish {
        int x, y, dir;

        Fish(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    static Cell[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int fishNum = Integer.parseInt(st.nextToken());
        int playNum = Integer.parseInt(st.nextToken());

        init();
        for (int i = 0; i < fishNum; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int fish = Integer.parseInt(st.nextToken()) - 1;

            board[x][y].fishes.add(fish);
        }

        st = new StringTokenizer(br.readLine());
        sharkX = Integer.parseInt(st.nextToken()) - 1;
        sharkY = Integer.parseInt(st.nextToken()) - 1;
        board[sharkX][sharkY].isShark = true;
        simulation(playNum);
    }

    static void init() {
        board = new Cell[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    static void simulation(int playNum) {

        for (int i = 0; i < playNum; i++) {
            // 1. 물고기 복제
            List<Fish> fishes = cloneFishes();

            // 2. 물고기 이동
            board = moveFishes();

            // 3. 상어 이동
            sharkMove();

            // 4. 물고기 냄새 제거
            removeScent();

            // 5. 복제 완료
            finishClone(fishes);
        }

        System.out.println(getTotalFishes());
    }

    static List<Fish> cloneFishes() {
        List<Fish> fishes = new ArrayList<>();

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                for (int dir : board[i][j].fishes) {
                    fishes.add(new Fish(i, j, dir));
                }
            }
        }

        return fishes;
    }

    static Cell[][] moveFishes() {
        Cell[][] nextBoard = new Cell[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                nextBoard[i][j] = new Cell();
                nextBoard[i][j].isShark = board[i][j].isShark;
                nextBoard[i][j].status = board[i][j].status;
            }
        }

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                for (int dir : board[i][j].fishes) {
                    boolean move = false;
                    int nd = dir;
                    for (int d = 0; d < 8; d++) {
                        int nx = i + dx[nd];
                        int ny = j + dy[nd];

                        if (canMove(nx, ny)) {
                            nextBoard[nx][ny].fishes.add(nd);
                            move = true;
                            break;
                        }

                        nd = (nd + 7) % 8;
                    }

                    if (!move) {
                        nextBoard[i][j].fishes.add(dir);
                    }
                }
            }
        }

        return nextBoard;
    }

    static void sharkMove() {
        visited = new boolean[BOARD_SIZE][BOARD_SIZE];
        maxFishCnt = -1;
        tempSharkMoves = new int[SHARK_MOVE];
        sharkMoves = null;
        board[sharkX][sharkY].isShark = false;

        permutation(sharkX, sharkY, 0, 0);

        int nx = sharkX, ny = sharkY;
        for (int i = 0; i < SHARK_MOVE; i++) {
            int dir = sharkMoves[i];
            nx += sx[dir];
            ny += sy[dir];

            if (!board[nx][ny].fishes.isEmpty()) {
                board[nx][ny].fishes.clear();
                board[nx][ny].status = SMELL_2;
            }
        }

        sharkX = nx;
        sharkY = ny;
        board[sharkX][sharkY].isShark = true;
    }

    static void permutation(int x, int y, int idx, int fishCnt) {
        if (idx == SHARK_MOVE) {
            if (fishCnt > maxFishCnt) {
                maxFishCnt = fishCnt;
                sharkMoves = tempSharkMoves.clone();
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + sx[i];
            int ny = y + sy[i];

            if (!inRange(nx, ny)) continue;

            tempSharkMoves[idx] = i;

            if (visited[nx][ny]) {
                permutation(nx, ny, idx + 1, fishCnt);
            } else {
                visited[nx][ny] = true;
                int curFishCnt = board[nx][ny].fishes.size();
                permutation(nx, ny, idx + 1, fishCnt + curFishCnt);
                visited[nx][ny] = false;
            }
        }
    }

    static void removeScent() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j].status == SMELL_2 || board[i][j].status == SMELL_1 || board[i][j].status == 1) {
                    board[i][j].status -= 1;
                }
            }
        }
    }

    static void finishClone(List<Fish> fishes) {
        for (Fish fish : fishes) {
            board[fish.x][fish.y].fishes.add(fish.dir);
        }
    }

    static boolean canMove(int x, int y) {
        return inRange(x, y) && board[x][y].status == NONE && !board[x][y].isShark;
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE;
    }

    static int getTotalFishes() {
        int total = 0;

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                total += board[i][j].fishes.size();
            }
        }

        return total;
    }
}
