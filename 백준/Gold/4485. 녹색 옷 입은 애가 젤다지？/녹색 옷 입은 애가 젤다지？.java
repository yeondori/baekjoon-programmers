import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    final static int INF = 987654321;
    static int N;
    static int[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int cost;

        public Node(int x, int y, int cost) {
            super();
            this.x = x;
            this.y = y;
            this.cost = cost;
        }


        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();
        String input;
        int problemNum = 1;
        while (!(input = br.readLine()).equals("0")) {
            N = Integer.parseInt(input);
            board = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int blackRupee = dijkstra();
            answer.append("Problem ").append(problemNum++).append(": ").append(blackRupee).append("\n");
        }
        System.out.println(answer);
    }

    private static int dijkstra() {
        int[][] rupees = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(rupees[i], INF);
        }
        rupees[0][0] = board[0][0];
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(0, 0, board[0][0]));

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.cost > rupees[cur.x][cur.y]) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }

                int newCost = rupees[cur.x][cur.y] + board[nx][ny];
                if (rupees[nx][ny] > newCost) {
                    rupees[nx][ny] = newCost;
                    q.offer(new Node(nx, ny, newCost));
                }
            }
        }

        return rupees[N - 1][N - 1];
    }
}
