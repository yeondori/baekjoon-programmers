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
        List<Integer> negatives = new ArrayList<>();
        List<Integer> positives = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        int number, zeros = 0;
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

        Deque<Integer> negSequence = new ArrayDeque();
        Deque<Integer> povSequence = new ArrayDeque();

        addSequnce(negSequence, negatives);
        for (int i = 0; i < zeros; i++) {
            finalSequence.append("0 ");
        }
        addSequnce(povSequence, positives);
        System.out.println(finalSequence);
    }

    private static void addSequnce(Deque<Integer> sequence, List<Integer> numbers) {
        boolean flag = false;

        for (int number : numbers) {
            if (flag) {
                sequence.addFirst(number);
            } else {
                sequence.addLast(number);
            }
            flag = !flag;
        }

        if (!numbers.isEmpty()) {
            if (Math.abs(sequence.peekFirst()) > Math.abs(sequence.peekLast())) {
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
