import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, studentNum;
    static boolean[] switches; //100개 이하, 20개씩

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        switches = new boolean[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int s = Integer.parseInt(st.nextToken());
            switches[i] = s != 0;
        }
        studentNum = Integer.parseInt(br.readLine());
        for (int i = 0; i < studentNum; i++) {
            st = new StringTokenizer(br.readLine());

            int gender = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            turnSwitch(gender, num);
        }
        printSwitches();
    }

    private static void printSwitches() {
        for (int i = 0; i < N; i++) {
            int s = switches[i] ? 1 : 0;
            System.out.print(s + " ");
            if ((i + 1) % 20 == 0) {
                System.out.print("\n");
            }
        }
    }

    private static void turnSwitch(int gender, int num) {
        if (gender == 1) {
            for (int i = 0; i < N; i++) {
                if ((i + 1) % num == 0) {
                    switches[i] = !switches[i];
                }
            }
        } else {
            search(num - 1, 1);
            switches[num-1] = !switches[num-1];
        }
    }

    private static void search(int idx, int depth) {
        if (idx - depth < 0 || idx + depth >= N) {
            return;
        }

        if (switches[idx - depth] == switches[idx + depth]) {

            switches[idx - depth] = !switches[idx - depth];
            switches[idx + depth] = !switches[idx + depth];

            search(idx, depth + 1);
        }
    }
}

