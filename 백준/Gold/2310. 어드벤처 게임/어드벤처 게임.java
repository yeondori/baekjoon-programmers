import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final String TROLL = "T", LEPRECHAUN = "L";
    static int N;

    static class Room {
        String type;
        int num;

        public Room(String type, int num) {
            this.type = type;
            this.num = num;
        }
    }

    static class State {
        int pos;
        int gold;

        public State(int pos, int gold) {
            this.pos = pos;
            this.gold = gold;
        }
    }

    static Room[] roomInfo;
    static List<Integer>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String mazeNum;
        while (!(mazeNum = br.readLine()).equals("0")) {
            N = Integer.parseInt(mazeNum);
            roomInfo = new Room[N + 1];
            adj = new List[N + 1];
            for (int i = 1; i <= N; i++) {
                adj[i] = new ArrayList<>();
            }

            for (int i = 1; i <= N; i++) {
                String[] input = br.readLine().split(" ");
                String type = input[0];
                int num = Integer.parseInt(input[1]);
                roomInfo[i] = new Room(type, num);

                for (int j = 2; j < input.length - 1; j++) {
                    adj[i].add(Integer.parseInt(input[j]));
                }
            }

            if (bfs(1)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }

    private static boolean bfs(int startNum) {
        Queue<State> q = new ArrayDeque<>();
        q.add(new State(startNum, 0));
        boolean[][] visit = new boolean[N + 1][1001];  // [방번호][금화양]

        while (!q.isEmpty()) {
            State cur = q.poll();
            int idx = cur.pos;
            int gold = cur.gold;

            for (int neighbor : adj[idx]) {
                int newGold = gold;

                // 트롤이 있는 방으로 이동할 때
                if (roomInfo[neighbor].type.equals(TROLL)) {
                    if (newGold < roomInfo[neighbor].num) {
                        continue;
                    }
                    newGold -= roomInfo[neighbor].num;
                }

                // 레프리콘이 있는 방으로 이동할 때
                if (roomInfo[neighbor].type.equals(LEPRECHAUN)) {
                    if (newGold < roomInfo[neighbor].num) {
                        newGold = roomInfo[neighbor].num;
                    }
                }

                // 도착 방에 도달할 때
                if (neighbor == N) {
                    return true;
                }

                // 새로운 상태 방문 여부 확인
                if (!visit[neighbor][newGold]) {
                    q.add(new State(neighbor, newGold));
                    visit[neighbor][newGold] = true;
                }
            }
        }
        return false;
    }
}
