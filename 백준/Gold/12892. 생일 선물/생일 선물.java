import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Gift implements Comparable<Gift> {
        int price;
        long value;

        public Gift(int price, long value) {
            this.price = price;
            this.value = value;
        }

        @Override
        public int compareTo(Gift o) {
            return Integer.compare(this.price, o.price);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long D = Long.parseLong(st.nextToken());

        Gift[] gifts = new Gift[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int price = Integer.parseInt(st.nextToken());
            long value = Long.parseLong(st.nextToken());
            gifts[i] = new Gift(price, value);
        }

        Arrays.sort(gifts);

        long maxHappiness = 0;
        long currentHappiness = 0;
        int start = 0;

        for (int end = 0; end < N; end++) {
            currentHappiness += gifts[end].value;

            while (gifts[end].price - gifts[start].price >= D) {
                currentHappiness -= gifts[start].value;
                start++;
            }

            maxHappiness = Math.max(maxHappiness, currentHappiness);
        }

        System.out.println(maxHappiness);
    }
}
