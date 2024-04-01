import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // https://velog.io/@lio8625/%EB%B0%B1%EC%A4%80-15732-JAVA-%EB%8F%84%ED%86%A0%EB%A6%AC-%EC%88%A8%EA%B8%B0%EA%B8%B0 참고
    static int N, K, D;

    static class Rule {
        int start;
        int end;
        int gap;

        public Rule(int start, int end, int gap) {
            this.start = start;
            this.end = end;
            this.gap = gap;
        }
    }

    static Rule[] rules;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        rules = new Rule[K];

        for (int r = 0; r < K; r++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int gap = Integer.parseInt(st.nextToken());

            rules[r] = new Rule(start, end, gap);
        }

        System.out.println(findBoxNum());
    }

    private static int findBoxNum() {
        int left = 0;
        int right = N;
        int boxNum = N;

        while (left <= right) {
            int mid = (left + right) / 2;
            long dotoriNum = getDotoriNum(mid);

            if (dotoriNum >= D) {
                right = mid - 1;
                if (boxNum > mid) {
                    boxNum = mid;
                }
                continue;
            }
            left = mid + 1;
        }
        return boxNum;
    }

    private static long getDotoriNum(int curBoxNum) {
        long dotori = 0;
        for (Rule rule : rules) {
            if (rule.start > curBoxNum) {
                continue;
            }
            int maxBoxNum = Math.min(rule.end, curBoxNum);
            dotori += (maxBoxNum - rule.start) / rule.gap + 1;
        }
        return dotori;
    }
}
