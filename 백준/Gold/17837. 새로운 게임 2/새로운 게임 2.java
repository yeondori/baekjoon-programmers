import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    final static int MAX_PIECES = 4, MAX_TURN = 1_000;

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
        ArrayDeque<Piece> curPieces;

        public Board(int status) {
            this.status = status;
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
            movePieces();
            // 게임 종료 조건
            if(isFin) {
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

            if (outOfRange(nx, ny) || checkerBoard[nx][ny].status == 2) {
                // 방향 전환
                p.dir = changeDir(p.dir);
                nx = p.x + dx[p.dir];
                ny = p.y + dy[p.dir];
                if (outOfRange(nx, ny) || checkerBoard[nx][ny].status == 2) {
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

        ArrayDeque<Piece> movePieces = new ArrayDeque<>();
        while (!origin.isEmpty()) {
            Piece top = origin.pollLast();
            movePieces.addFirst(top);
            if (top.num == p.num) break;
        }

        if (checkerBoard[nx][ny].status == 1) {
            ArrayDeque<Piece> temp = new ArrayDeque<>();
            while (!movePieces.isEmpty()) {
                temp.addLast(movePieces.pollLast());
            }
            movePieces = temp;
        }

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
