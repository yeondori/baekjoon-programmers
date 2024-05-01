import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int HomeworkNum;

    static class Homework implements Comparable<Homework> {
        int time, dueDate;

        Homework(int time, int dueDate) {
            this.time = time;
            this.dueDate = dueDate;
        }

        @Override
        public int compareTo(Homework o) {
            return o.dueDate - this.dueDate;
        }
    }

    static PriorityQueue<Homework> homeworks = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        HomeworkNum = Integer.parseInt(br.readLine());
        int maxDuedate = 0;
        for (int i = 0; i < HomeworkNum; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int dueDate = Integer.parseInt(st.nextToken());

            if (dueDate > maxDuedate) {
                maxDuedate = dueDate;
            }
            homeworks.add(new Homework(time, dueDate));
        }

        doHomework(maxDuedate);
    }

    private static void doHomework(int maxDueDate) {
        int day  = maxDueDate;
        int hwStart = 0;
        while(!homeworks.isEmpty()) {
            Homework hw = homeworks.poll();
            day = Math.min(day, hw.dueDate);

            hwStart = day - hw.time;
            day = hwStart;
        }

        System.out.println(hwStart);
    }
}
