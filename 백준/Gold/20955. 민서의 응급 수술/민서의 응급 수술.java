import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] parents;

    static void init(int n) {
        parents = new int[n + 1];
        Arrays.fill(parents, -1);
    }

    static int find(int a) {
        if (parents[a] < 0) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return false; 
        if (parents[a] < parents[b]) {
            parents[a] += parents[b];
            parents[b] = a;
        } else {
            parents[b] += parents[a];
            parents[a] = b;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        init(n);  

        int extraEdges = 0; 
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if (!union(u, v)) {
                extraEdges++;  
            }
        }

        int components = 0;
        for (int i = 1; i <= n; i++) {
            if (parents[i] < 0) { 
                components++;
            }
        }

        extraEdges += (components - 1);
        System.out.println(extraEdges);
    }
}
