import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int testCase, totalPrice, friendsNum;

    static class Friend implements Comparable<Friend> {
        int idx, pay, afford;

        public Friend(int idx, int pay, int afford) {
            this.idx =idx;
            this.pay = pay;
            this.afford = afford;
        }

        @Override
        public int compareTo(Friend o) {
            return this.afford - o.afford;
        }
    }

    static Friend[] friends;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        testCase = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < testCase; tc++) {
            st = new StringTokenizer(br.readLine());
            totalPrice = Integer.parseInt(st.nextToken());
            friendsNum = Integer.parseInt(st.nextToken());
            int fairShare = totalPrice / friendsNum;
            friends = new Friend[friendsNum];

            st = new StringTokenizer(br.readLine());
            List<Friend> morePay = new ArrayList<>();
            int cur, totalSum = 0, curSum = 0;
            for (int i = 0; i < friendsNum; i++) {
                cur = Integer.parseInt(st.nextToken());

                totalSum += cur;
                int pay = Math.min(fairShare, cur);
                curSum += pay;
                friends[i] = new Friend(i, pay, cur);
                if (pay < cur) {
                    morePay.add(friends[i]);
                }
            }

            if (totalPrice > totalSum) {
                answer.append("IMPOSSIBLE").append("\n");
            } else if (totalPrice == curSum) {
                for (int i = 0; i < friendsNum; i++) {
                    answer.append(friends[i].pay).append(" ");
                }
                answer.append("\n");
            } else {
                morePay.sort((o1, o2) -> o2.afford - o1.afford);
                Queue<Friend> q = new ArrayDeque<>(morePay);

                while (!q.isEmpty()) {
                    Friend curFr = q.poll();

                    curSum++;
                    curFr.pay++;
                    if (curFr.pay < curFr.afford) {
                        q.add(curFr);
                    } else {
                        friends[curFr.idx].pay = curFr.pay;
                    }

                    if (curSum == totalPrice) {
                        break;
                    }
                }

                for (int i = 0; i < friendsNum; i++) {
                    answer.append(friends[i].pay).append(" ");
                }
                answer.append("\n");
            }
        }
        System.out.print(answer);
    }
}
