import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static StringBuilder finalSequence;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        finalSequence = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        List<Long> negatives = new ArrayList<>();
        List<Long> positives = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        long number, zeros = 0;
        for (int i = 0; i < N; i++) {
            number = Integer.parseInt(st.nextToken());

            if (number < 0) {
                negatives.add(number);
            } else if (number == 0) {
                zeros++;
            } else {
                positives.add(number);
            }
        }

        Collections.sort(positives, Collections.reverseOrder());
        Collections.sort(negatives);

        Deque<Long> negSequence = new ArrayDeque();
        Deque<Long> povSequence = new ArrayDeque();

        addSequnce(negSequence, negatives, false);
        for (int i = 0; i < zeros; i++) {
            finalSequence.append("0 ");
        }
        addSequnce(povSequence, positives, true);
        System.out.println(finalSequence);
    }

    private static void addSequnce(Deque<Long> sequence, List<Long> numbers, boolean isPositive) {
        boolean flag = false;

        for (long number : numbers) {
            if (flag) {
                sequence.addFirst(number);
            } else {
                sequence.addLast(number);
            }
            flag = !flag;
        }

        if (!numbers.isEmpty()) {
            if (isPositive) {
                if (sequence.peekFirst() > sequence.peekLast()) {
                    while (!sequence.isEmpty()) {
                        finalSequence.append(sequence.pollLast()).append(" ");
                    }
                } else {
                    while (!sequence.isEmpty()) {
                        finalSequence.append(sequence.pollFirst()).append(" ");
                    }
                }
            } else {
                if (sequence.peekFirst() > sequence.peekLast()) {
                    while (!sequence.isEmpty()) {
                        finalSequence.append(sequence.pollLast()).append(" ");
                    }
                } else {
                    while (!sequence.isEmpty()) {
                        finalSequence.append(sequence.pollFirst()).append(" ");
                    }
                }
            }
        }
    }
}
