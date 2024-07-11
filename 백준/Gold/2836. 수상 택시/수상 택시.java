import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
// https://sa11k.tistory.com/62
    static int num, end;
    static long answer;
    static class Route implements Comparable<Route> {
        long str, end;

        public Route(int str, int end) {
            this.str = str;
            this.end = end;
        }


        @Override
        public int compareTo(Route o) {
            if (this.end == o.end) {
                return Long.compare(this.str, o.str);
            } else {
                return Long.compare(this.end, o.end);
            }
        }
    }

    static List<Route> reverseRoutes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        num = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        answer = end;
        reverseRoutes = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine());

            int str = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            if (str > end) {
                reverseRoutes.add(new Route(str, end));
            }
        }
        
        solve();
    }

    private static void solve() {
        Collections.sort(reverseRoutes);

        long min = reverseRoutes.get(0).end;
        long max = reverseRoutes.get(0).str;

        Route cur;
        for (int i = 1, size = reverseRoutes.size(); i < size; i++) {
            cur = reverseRoutes.get(i);

            if (cur.end <= max) {
                max = Math.max(max, cur.str);
            } else {
                answer += 2 * (max - min);
                max = cur.str;
                min = cur.end;
            }
        }

        answer += 2 * (max - min);
        System.out.println(answer);
    }
}
