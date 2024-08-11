import java.io.*;
import java.util.StringTokenizer;

//https://velog.io/@silver_star/%EB%B0%B1%EC%A4%80-1493-%EB%B0%95%EC%8A%A4-%EC%B1%84%EC%9A%B0%EA%B8%B0-Greedy-Divide-And-Conquer
public class Main {
    static int length, width, height;
    static int[] cubes = new int[20];
    static int minCount;
    static boolean possible;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        length = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());
            cubes[size] = count;
        }

        fillBox(length, width, height);

        System.out.println(possible ? minCount : -1);
    }

    static void fillBox(int l, int w, int h) {
        if (l == 0 || w == 0 || h == 0) {
            return;
        }

        possible = false;
        int cubeSize = 0;

        for (int i = 19; i >= 0; i--) {
            if (cubes[i] == 0) continue;

            cubeSize = 1 << i;
            if (l >= cubeSize && w >= cubeSize && h >= cubeSize) {
                minCount++;
                cubes[i]--;
                possible = true;
                break;
            }
        }

        if (!possible) return;

        fillBox(cubeSize, w - cubeSize, cubeSize);
        fillBox(l - cubeSize, w, cubeSize);
        fillBox(l, w, h - cubeSize);
    }
}