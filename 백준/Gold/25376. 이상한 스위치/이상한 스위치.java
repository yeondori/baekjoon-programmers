import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int switchNumber;
    static int switchStatus;
    static List<Integer>[] switches;

    static class Choice {
        int idx;
        int cnt;

        Choice(int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        switchNumber = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < switchNumber; i++) {
            if (st.nextToken().equals("1")) {
                switchStatus |= 1 << i;
            }
        }
        
        switches = new ArrayList[switchNumber];
        for (int i = 0; i < switchNumber; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            switches[i] = new ArrayList<>();
            for (int j = 0; j < num; j++) {
                int next = Integer.parseInt(st.nextToken()) - 1;
                switches[i].add(next);
            }
        }
        
        System.out.println(solve(switchStatus));
    }

    private static int solve(int start) {
        boolean[] visit = new boolean[1 << switchNumber];
        Queue<Choice> q = new LinkedList<>();
        q.add(new Choice(start, 0));
        visit[start] = true;

        while (!q.isEmpty()) {
            Choice cur = q.poll();
            int curBit = cur.idx;
            int curCnt = cur.cnt;

            if (curBit == (1 << switchNumber) - 1) {
                return curCnt;
            }

            for (int i = 0; i < switchNumber; i++) {
                if ((curBit & (1<< i)) != 0) continue;

                int next = curBit;
                next ^= (1 << i);
                for (int adj : switches[i]) {
                    next ^= (1 << adj);
                }

                if (visit[next]) continue;
                q.add(new Choice(next, curCnt + 1));
                visit[next] = true;
            }
        }
        return -1;
    }
}
