import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    final static int MAX_PIECES = 4, MAX_TURN = 1_000;
    final static int WHITE = 0, RED = 1, BLUE = 2;

    static class Piece {
        int num, x, y, dir;

        Piece(int num, int x, int y, int dir) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    static class Board {
        int status;
        boolean isReversed;
        ArrayDeque<Piece> curPieces;

        public Board(int status) {
            this.status = status;
            this.isReversed = false;
            this.curPieces = new ArrayDeque<>();
        }
    }

    static int boardSize, pieceNum;
    static Piece[] pieces;
    static Board[][] checkerBoard;
    static int[] dx = {0, 0, 0, -1, 1}; // 입력 우좌상하 순
    static int[] dy = {0, 1, -1, 0, 0};
    static boolean isFin;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        boardSize = Integer.parseInt(st.nextToken());
        pieceNum = Integer.parseInt(st.nextToken());

        checkerBoard = new Board[boardSize][boardSize];
        for (int row = 0; row < boardSize; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < boardSize; col++) {
                int status = Integer.parseInt(st.nextToken());
                checkerBoard[row][col] = new Board(status);
                if (status == RED) {
                    checkerBoard[row][col].isReversed = true;
                }
            }
        }

        pieces = new Piece[pieceNum];
        for (int pIdx = 0; pIdx < pieceNum; pIdx++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());

            Piece piece = new Piece(pIdx, x, y, dir);
            pieces[pIdx] = piece;
            checkerBoard[x][y].curPieces.add(piece);
        }

        int turnNum = simulation();
        System.out.println(turnNum);
    }

    private static int simulation() {
        for (int turn = 1; turn <= MAX_TURN; turn++) {
            // 말 이동
            movePieces();
            if (isFin) {
                return turn;
            }
        }
        return -1;
    }
    
    private static void movePieces() {
        for (int pIdx = 0; pIdx < pieceNum; pIdx++) {
            Piece p = pieces[pIdx];
            int nx = p.x + dx[p.dir];
            int ny = p.y + dy[p.dir];

            // 1-1. 범위를 벗어나거나 파란색일 경우 방향 전환
            if (outOfRange(nx, ny) || checkerBoard[nx][ny].status == BLUE) {
                p.dir = changeDir(p.dir);
                nx = p.x + dx[p.dir];
                ny = p.y + dy[p.dir];
                // 방향 바꿨을 때도 범위를 벗어나거나 파란색이면 이동하지 않음
                if (outOfRange(nx, ny) || checkerBoard[nx][ny].status == BLUE) {
                    continue;
                }
            }

            movePiece(p, nx, ny);
            if (isFin) return;
        }
    }

    private static void movePiece(Piece p, int nx, int ny) {
        ArrayDeque<Piece> origin = checkerBoard[p.x][p.y].curPieces;
        ArrayDeque<Piece> destination = checkerBoard[nx][ny].curPieces;

        // 1-2. 이동할 말들만 선택
        ArrayDeque<Piece> movePieces = new ArrayDeque<>();
        while (!origin.isEmpty()) {
            Piece top = origin.pollLast();
            movePieces.addFirst(top);
            if (top.num == p.num) break;
        }

        // 2. 빨간색인 경우 선택된 말들의 순서를 뒤집음
        if (checkerBoard[nx][ny].status == RED) {
            ArrayDeque<Piece> temp = new ArrayDeque<>();
            while (!movePieces.isEmpty()) {
                temp.addLast(movePieces.pollLast());
            }
            movePieces = temp;
        }

        // 3. 선택된 말들을 새 위치로 이동
        while (!movePieces.isEmpty()) {
            Piece curPiece = movePieces.pollFirst();
            curPiece.x = nx;
            curPiece.y = ny;
            destination.addLast(curPiece);
        }

        // 4. 4개 이상 쌓이면 게임 종료
        if (destination.size() >= MAX_PIECES) {
            isFin = true;
        }
    }
    
    private static int changeDir(int dir) {
        if (dir == 1) {
            return 2;
        } else if (dir == 2) {
            return 1;
        } else if (dir == 3) {
            return 4;
        }
        return 3;
    }

    private static boolean outOfRange(int nx, int ny) {
        return nx < 0 || nx >= boardSize || ny < 0 || ny >= boardSize;
    }
}

