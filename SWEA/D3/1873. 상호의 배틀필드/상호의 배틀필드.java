import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int T, H, W, N;
    static char[][] map;
    static int direction, posX, posY;
    static boolean isMove;
    static char[] dir = {'^', 'v', '<', '>'};
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            map = new char[H][W];

            for (int i = 0; i<H; i++) {
                String input = br.readLine();
                for (int j = 0; j<W; j++) {
                    map[i][j] = input.charAt(j);
                    switch (map[i][j]) {
                        case '^' :
                            direction = 0;
                            posX = i;
                            posY = j;
                            break;
                        case 'v' :
                            direction = 1;
                            posX = i;
                            posY = j;
                            break;
                        case '<' :
                            direction = 2;
                            posX = i;
                            posY = j;
                            break;
                        case '>' :
                            direction = 3;
                            posX = i;
                            posY = j;
                            break;
                    }
                }
            }
            N = Integer.parseInt(br.readLine());
            gameStart(br.readLine().split(""));
            sb.append(printMap("#" + tc + " "));
        }
        System.out.println(sb);
    }


    private static void gameStart(String[] commands) {
        for (int i = 0; i < N; i++) {
            int nx, ny;
            switch (commands[i]) {
                case "S" :
                    nx = posX;
                    ny = posY;
                    while (true) {
                        nx += dx[direction];
                        ny += dy[direction];

                        if (nx < 0 || nx >= H || ny <0 || ny>=W) {
                            break;
                        }
                        if (map[nx][ny] == '*') {
                            map[nx][ny] = '.';
                            break;
                        }
                        if (map[nx][ny] == '#') {
                            break;
                        }
                    }
                    break;
                case "U" :
                    direction = 0;
                    isMove = true;
                    break;
                case "D" :
                    direction = 1;
                    isMove = true;
                    break;
                case "L" :
                    direction = 2;
                    isMove = true;
                    break;
                case "R" :
                    direction = 3;
                    isMove = true;
                    break;
            }

            if (isMove) {
                nx = posX + dx[direction];
                ny = posY+ dy[direction];

                if (nx>-1 && nx < H && ny >-1 && ny <W && map[nx][ny] == '.') {
                    map[posX][posY] = '.';

                    posX = nx;
                    posY = ny;
                }
                map[posX][posY] = dir[direction];
                isMove = false;
            }
        }
    }

    private static String printMap(String prefix) {
        StringBuilder answer = new StringBuilder(prefix);
        for (int i=0; i<H; i++) {
            for (int j=0; j<W; j++) {
                answer.append(map[i][j]);
            }
            answer.append("\n");
        }
        return answer.toString();
    }
}
