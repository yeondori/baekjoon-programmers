import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int END = 100;
    static Map<Integer, Integer> snakesAndLadders = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int snakeNum = Integer.parseInt(st.nextToken());
        int ladderNum = Integer.parseInt(st.nextToken());

        for (int i = 0; i < snakeNum + ladderNum; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            snakesAndLadders.put(from, to);
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[END + 1];
        queue.offer(1); // 시작은 1번 칸
        visited[1] = true;

        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int current = queue.poll();

                for (int dice = 1; dice <= 6; dice++) {
                    int next = current + dice;
                    if (next > END) continue;

                    if (snakesAndLadders.containsKey(next)) {
                        next = snakesAndLadders.get(next);
                    }

                    if (next == END) {
                        return moves + 1;
                    }

                    if (!visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }
            moves++;
        }
        return -1; // 도착할 수 없는 경우
    }
}
