import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Main {

    static long[] tree;

    public static long init(int start, int end, int node) {
        if (start == end) { // leaf 노드
            return tree[node] = 1;
        }
        int mid = (start + end) >> 1; // /2 보다 훨씬 빠르다!
        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    public static void update(int start, int end, int node, long index) {
        tree[node]--;
        if (start == end) { // 단말 노드
            answer.add((start + 1)+ "");
            return;
        }
        int mid = (start + end) >> 1;
        if (tree[node * 2] > index) {
            update(start, mid, node * 2, index); // 왼쪽 노드
        } else {
            update(mid + 1, end, node * 2 + 1, index - tree[node * 2]); // 오른쪽 노드
        }
    }

    static int N, K;
    static long pos;
//    static StringBuilder answer;
    static StringJoiner answer = new StringJoiner(", ", "<", ">");

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        tree = new long[N << 2];

        init(0, N - 1, 1);

        pos = 0;
        while (tree[1] > 0) {
            pos += K - 1;
            pos %= tree[1];
            update(0, N - 1, 1, pos);
        }

        System.out.println(answer);
    }
}
