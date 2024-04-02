import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int T, N;
    static int[] preorder, inorder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());
            preorder = new int[N];
            inorder = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                preorder[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                inorder[i] = Integer.parseInt(st.nextToken());
            }
            findPreorder(0, 0, N);
            System.out.println();
        }
    }

    private static void findPreorder(int root, int str, int end) {
        for (int i = str; i < end; i++) {
            if (preorder[root] == inorder[i]) {
                findPreorder(root + 1, str, i);
                findPreorder(root + (i + 1 - str), i + 1, end);
                System.out.print(preorder[root] + " ");
            }
        }
    }
}
