import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Lecture implements Comparable<Lecture> {
        int pay, dueDay;

        Lecture(int pay, int dueDay) {
            this.pay = pay;
            this.dueDay = dueDay;
        }

        @Override
        public int compareTo(Lecture o) {
            return o.pay - this.pay;
        }
    }

    ;

    static PriorityQueue<Lecture> pq;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        pq = new PriorityQueue<Lecture>();
        int lectureNum = Integer.parseInt(br.readLine());
        for (int i = 0; i < lectureNum; i++) {
            st = new StringTokenizer(br.readLine());

            int pay = Integer.parseInt(st.nextToken());
            int dueDate = Integer.parseInt(st.nextToken());

            pq.add(new Lecture(pay, dueDate));
        }

        solve();
    }

    private static void solve() {
        int totalPay = 0;
        boolean[] date = new boolean[10001];

        while (!pq.isEmpty()) {
            Lecture cur = pq.poll();

            for (int i = cur.dueDay; i >= 1; i--) {
                if (date[i]) continue;

                date[i] = true;
                totalPay += cur.pay;
                break;
            }
        }

        System.out.println(totalPay);
    }
}
