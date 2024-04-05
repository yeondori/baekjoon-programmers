import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

public class Solution {
    static int TestCaseNum, BoardSize, EndTime, GroupNumber, totalMicroNum;
    static int[] dx = {0, -1, 1, 0, 0};    // 입력은 상하좌우
    static int[] dy = {0, 0, 0, -1, 1};

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static class MicroBioGroup {
        int size, dir, addSize;

        public MicroBioGroup(int size, int dir) {
            this.size = size;
            this.dir = dir;
            this.addSize = 0;
        }
    }

    static Map<Point, MicroBioGroup> microbios;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        // 1. 입력 받기
        TestCaseNum = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TestCaseNum; tc++) {
            st = new StringTokenizer(br.readLine());
            BoardSize = Integer.parseInt(st.nextToken());
            EndTime = Integer.parseInt(st.nextToken());
            GroupNumber = Integer.parseInt(st.nextToken());

            microbios = new HashMap<>();
            totalMicroNum = 0;
            for (int groupIdx = 0; groupIdx < GroupNumber; groupIdx++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int size = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());

                totalMicroNum += size;

                microbios.put(new Point(x, y), new MicroBioGroup(size, dir));
            }
            simulation();
            answer.append("#").append(tc).append(" ").append(totalMicroNum).append("\n");
        }
        System.out.println(answer);
    }

    private static void simulation() {
        // 2. EndTime까지 시뮬레이션
        for (int time = 0; time < EndTime; time++) {
            moveBio();
        }
        //  countRemainMicro();
    }

    private static void countRemainMicro() {
        totalMicroNum = 0;
        for (Point curPoint : microbios.keySet()) {
            MicroBioGroup curBio = microbios.get(curPoint);
            curBio.size += curBio.addSize;
            totalMicroNum += curBio.size;
        }
    }

    private static void moveBio() {
        Map<Point, MicroBioGroup> nextMicrobios = new HashMap<>();
        for (Point curPoint : microbios.keySet()) {
            MicroBioGroup curBio = microbios.get(curPoint);
            // 이동시키기 전에 addSize 초기화
            curBio.size += curBio.addSize;
            curBio.addSize = 0;
            // 3. 방향에 맞게 이동
            int nx = curPoint.x + dx[curBio.dir];
            int ny = curPoint.y + dy[curBio.dir];
            // 3-1. 경계에 있는 경우
            if (nx == 0 || nx == BoardSize-1 || ny == 0 || ny == BoardSize-1) {
                // 3-1.1 방향을 바꾸고
                curBio.dir = changeDir(curBio.dir);
                // 3-1.2. 절반이 죽는다.
                int halfSize = curBio.size/2;
                int dieMicroNum = curBio.size - halfSize;
                curBio.size = halfSize;
                totalMicroNum -= dieMicroNum;
                // 3-1.3. 사이즈가 0인 경우엔 값을 넣지 않는다.
                if (halfSize == 0) {
                    continue;
                }
            }
            // 3-2.1. 이동이 가능한 경우 다음 이동 위치를 확인한다.
            MicroBioGroup existMicro = nextMicrobios.get(new Point(nx, ny));
            if (existMicro!=null) {
                // 3-2.3 군집이 존재하면 사이즈를 비교하여 갱신한다.
                if (existMicro.size > curBio.size) {
                    existMicro.addSize += curBio.size;
                } else {
                    existMicro.addSize += existMicro.size;
                    existMicro.size = curBio.size;
                    existMicro.dir = curBio.dir;
                }
            } else {
                // 3-2.3. 이동할 위치에 군집이 존재하지 않으면 이동시킨다.
                nextMicrobios.put(new Point(nx, ny), curBio);
            }
        }
        microbios = nextMicrobios;
    }

    private static int changeDir(int dir) {
        if (dir == 1) return 2;
        else if (dir == 2) return 1;
        else if (dir == 3) return 4;
        return 3;
    }
}
