import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Member implements Comparable<Member> {
        int order;
        int age;
        String name;

        Member(int order, int age, String name) {
            this.order = order;
            this.age = age;
            this.name = name;
        }

        @Override
        public int compareTo(Member o) {
            return (this.age - o.age) == 0? (this.order - o.order) : (this.age - o.age);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Member> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();

            pq.add(new Member(i, age, name));
        }

        StringBuilder answer = new StringBuilder();
        while (!pq.isEmpty()) {
            Member curMember = pq.poll();

            answer.append(curMember.age)
                    .append(" ")
                    .append(curMember.name)
                    .append("\n");
        }

        System.out.println(answer);
    }
}
