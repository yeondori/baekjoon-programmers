import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://howtolivelikehuman.tistory.com/249
public class Main {

    // 방향 회전 수
    static int N;
    // 방문 체크 배열, 중앙 (25, 25)에서 시작
    static boolean[][] map = new boolean[51][51];

    // 이동 방향 배열 (좌표 변화량)
    static int[] moveX = {0, -1, 1, 0, -1, 1};
    static int[] moveY = {1, 1, 1, -1, -1, -1};

    // 방향 회전에 따른 다음 이동 방향
    static int[][] availableMove = {{2, 1}, {0, 4}, {5, 0}, {4, 5}, {1, 3}, {3, 2}};

    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map[25][25] = true; // 시작 위치 방문 체크
        seeking(0, 3, 25, 24); // 초기 방향은 남쪽 (pastMove = 3)
        System.out.println(answer);
    }

    public static void seeking(int count, int pastMove, int x, int y) {
        if (map[y][x]) {
            if (count == N) {
                answer++;
            }
            return;
        }
        if (count == N) return;

        map[y][x] = true; // 현재 위치 방문 체크
        for (int i = 0; i < 2; i++) {
            int nextMove = availableMove[pastMove][i];
            seeking(count + 1, nextMove, x + moveX[nextMove], y + moveY[nextMove]);
        }
        map[y][x] = false; // 백트래킹: 현재 위치 방문 해제
    }
}
