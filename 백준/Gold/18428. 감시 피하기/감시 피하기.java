import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    final static int OBSTACLE_NUMBER = 3;
    final static String OBSTACLE = "O";
    final static String STUDENT = "S";
    final static String TEACHER = "T";

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int N;
    static String[][] board;

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{");
            sb.append("x=").append(x);
            sb.append(", y=").append(y);
            sb.append('}');
            return sb.toString();
        }
    }

    static List<Point> teachers, blanks;
    static boolean canAvoid;
    static Point[] obstacles;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        board = new String[N][N];
        teachers = new ArrayList<>();
        blanks = new ArrayList<>();

        String[] input = new String[N];
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                if (input[j].equals(TEACHER)) {
                    teachers.add(new Point(i, j));
                } else if (!input[j].equals(STUDENT)) {
                    blanks.add(new Point(i, j));
                }
                board[i][j] = input[j];
            }
        }

        obstacles = new Point[OBSTACLE_NUMBER];
        combi(0, 0);
        System.out.println(canAvoid ? "YES" : "NO");
    }

    private static void combi(int start, int cnt) {
        if (canAvoid) {
            return;
        }

        if (cnt == OBSTACLE_NUMBER) {
            setBoard(OBSTACLE);
            check();
            setBoard("X");
            return;
        }

        for (int i = start; i < blanks.size(); i++) {
            obstacles[cnt] = blanks.get(i);
            combi(i + 1, cnt + 1);
        }
    }

    private static void check() {
        for (int i = 0; i < teachers.size(); i++) {
            if (findStudent(teachers.get(i))) {
                return;
            }
        }
        canAvoid = true; // 모든 선생님이 학생을 찾지 못한 경우
    }

    private static boolean findStudent(Point point) {
        int nx, ny;
        for (int i = 0; i < 4; i++) {
            nx = point.x;
            ny = point.y;

            while (!outOfRange(nx, ny)) {
                nx += dx[i];
                ny += dy[i];

                if (outOfRange(nx, ny)) {
                    break;
                }

                if (board[nx][ny].equals(STUDENT)) {
                    return true;
                }

                if (board[nx][ny].equals(OBSTACLE)) {
                    break;
                }
            }
        }

        return false;
    }

    private static boolean outOfRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N;
    }


    private static void setBoard(String x) {
        for (int i = 0; i < OBSTACLE_NUMBER; i++) {
            Point p = obstacles[i];
            board[p.x][p.y] = x;
        }
    }
}
