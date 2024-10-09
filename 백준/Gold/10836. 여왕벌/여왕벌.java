import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static final int GROWTH_TYPE = 3;
    static int honeyCombSize, days;

    static class Block {
        int size;
        int diff;

        Block() {
            this.size = 1;
            this.diff = 0;
        }
    }

    static Block[][] honeyComb;
    static List<Integer> growth;

    static int[] dx = {-1, -1, 0};
    static int[] dy = {0, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        honeyCombSize = Integer.parseInt(st.nextToken());
        days = Integer.parseInt(st.nextToken());

        honeyComb = new Block[honeyCombSize][honeyCombSize];
        for (int i = 0; i < honeyCombSize; i++) {
            for (int j = 0; j < honeyCombSize; j++) {
                honeyComb[i][j] = new Block();
            }
        }

        for (int day = 0; day < days; day++) {
            st = new StringTokenizer(br.readLine());
            growth = new ArrayList<>();
            for (int growthSize = 0; growthSize < GROWTH_TYPE; growthSize++) {
                int cnt = Integer.parseInt(st.nextToken());

                while (cnt-- > 0) {
                    growth.add(growthSize);
                }
            }

            grow();
        }

        printHoneyComb();
    }

    static void grow() {
        int x = honeyCombSize - 1, y = 0;
        for (int growthSize : growth) {
            honeyComb[x][y].size += growthSize;
            honeyComb[x][y].diff = growthSize;

            if (x > 0) {
                x--;
            } else {
                y++;
            }
        }

        for (int i = 1; i < honeyCombSize; i++) {
            for (int j = 1; j < honeyCombSize; j++) {
                int maxGrowth = 0;
                for (int d = 0; d < 3; d++) {
                    int aroundX = i + dx[d];
                    int aroundY = j + dy[d];

                    if (inRange(aroundX, aroundY)) {
                        maxGrowth = Math.max(maxGrowth, honeyComb[aroundX][aroundY].diff);
                    }
                }

                honeyComb[i][j].size += maxGrowth;
                honeyComb[i][j].diff = maxGrowth;
            }
        }
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && x < honeyCombSize && y >= 0 && y < honeyCombSize;
    }

    static void printHoneyComb() {
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < honeyCombSize; i++) {
            for (int j = 0; j < honeyCombSize; j++) {
                answer.append(honeyComb[i][j].size)
                        .append(" ");
            }
            answer.append("\n");
        }

        System.out.println(answer);
    }
}
