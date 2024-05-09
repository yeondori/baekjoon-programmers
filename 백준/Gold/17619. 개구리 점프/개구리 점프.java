import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int Num, QNum;

    static class Log implements Comparable<Log> {
        int idx, str, end;

        public Log(int idx, int str, int end) {
            this.idx = idx;
            this.str = str;
            this.end = end;
        }

        @Override
        public int compareTo(Log o) {
            return this.str - o.str == 0 ? this.end - o.end : this.str - o.str;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Log{");
            sb.append("idx=").append(idx);
            sb.append(", str=").append(str);
            sb.append(", end=").append(end);
            sb.append('}');
            return sb.toString();
        }
    }

    static Log[] logs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder answer = new StringBuilder();

        Num = Integer.parseInt(st.nextToken());
        QNum = Integer.parseInt(st.nextToken());

        logs = new Log[Num];
        for (int i = 0; i < Num; i++) {
            st = new StringTokenizer(br.readLine());

            int str = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            logs[i] = new Log(i + 1, str, end);
        }

        Arrays.sort(logs);

        int[] groups = new int[Num + 1];
        int groupNum = 1;
        int gEnd = logs[0].end;
        groups[logs[0].idx] = groupNum;
        for (int i = 1; i < Num; i++) {
            if (logs[i].str > gEnd) {
                groups[logs[i].idx] = ++groupNum;
                gEnd = logs[i].end;
            } else {
                groups[logs[i].idx] = groupNum;
                if (logs[i].end > gEnd) {
                    gEnd = logs[i].end;
                }
            }
        }

        for (int i = 0; i < QNum; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            if (groups[from] == groups[to]) {
                answer.append(1);
            } else {
                answer.append(0);
            }
            answer.append("\n");
        }

        System.out.println(answer);
    }
}
