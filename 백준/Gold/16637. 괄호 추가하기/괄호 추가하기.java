import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int length, max, numSize, operSize;
    static List<Integer> numbers;
    static List<Character> operators;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        length = Integer.parseInt(br.readLine());
        numbers = new ArrayList<>();
        operators = new ArrayList<>();

        char[] input = br.readLine().toCharArray();
        for (int i = 0; i < length; i++) {
            if ( i % 2 == 0) {
                numbers.add(input[i] - '0');
            } else {
                operators.add(input[i]);
            }
        }
        numSize = numbers.size();
        operSize = operators.size();
        solve();
    }

    private static void solve() {
        max = Integer.MIN_VALUE;
        recur(0, numbers.get(0));
        System.out.println(max);
    }

    private static void recur(int idx, int value) {
        if(idx == operSize) {
            max = Math.max(value, max);
            return;
        }

        int calWithBracket = calculate(operators.get(idx), value, numbers.get(idx+1));
        recur(idx+1, calWithBracket);

        if (idx + 2 <= numSize - 1) {
            int calWithoutBracket = calculate(operators.get(idx),
                    value, calculate(operators.get(idx + 1), numbers.get(idx + 1), numbers.get(idx + 2)));
            recur(idx + 2, calWithoutBracket);
        }
    }

    private static int calculate(char oper, int num1, int num2) {
        switch (oper) {
            case '+' :
                return num1 + num2;
            case '-' :
                return num1 - num2;
            case '*':
                return num1 * num2;
        }
        return 0;
    }
}
