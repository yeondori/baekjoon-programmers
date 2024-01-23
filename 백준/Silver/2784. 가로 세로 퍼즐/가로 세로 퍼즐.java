import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static final int INPUT_NUM = 6;
    static final int PUZZLE_NUM = 3;
    static int[] target = new int[]{0, 1, 2, 3, 4, 5};
    static boolean[] visited = new boolean[INPUT_NUM];
    static int[] result = new int[PUZZLE_NUM];

    static List<String> input = new ArrayList<>(INPUT_NUM);

    static boolean solved = false;

    static List<String> answers = new ArrayList<>(100);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < INPUT_NUM; i++) {
            input.add(br.readLine());
        }

        permutation(0);

        Optional<String> firstAnswer = answers.stream()
                .min(Comparator.naturalOrder());
        printAnswer(firstAnswer.orElse("0"));
    }

    static String makePuzzle(int[] index) {
        String[][] puzzle = new String[PUZZLE_NUM][PUZZLE_NUM];
        List<String> leftInput = new ArrayList<>(input);

        for (int i = 0; i < PUZZLE_NUM; i++) {
            int idx = index[i];
            leftInput.remove(input.get(idx));
            puzzle[i] = input.get(idx).split("");
        }
        if (validatePuzzle(puzzle, leftInput)) {
            solved = true;
            return Arrays.stream(puzzle)
                    .flatMap(Arrays::stream)
                    .collect(Collectors.joining(""));
        }
        return null;
    }

    private static boolean validatePuzzle(String[][] puzzle, List<String> leftInput) {
        for (int i = 0; i < PUZZLE_NUM; i++) {
            StringBuilder word = new StringBuilder();
            for (int j = 0; j < PUZZLE_NUM; j++) {
                word.append(puzzle[j][i]);
            }
            if (!leftInput.contains(word.toString())) {
                return false;
            }
            leftInput.remove(word.toString());
        }

        return leftInput.isEmpty();
    }

    private static void permutation(int cnt) {
        if (cnt == PUZZLE_NUM) {
            String answer = makePuzzle(result);
            if (solved && answer != null) {
                answers.add(answer);
            }
            return;
        }
        // 대상 집합을 순회하며 숫자를 하나 선택한다.
        for (int i = 0; i < INPUT_NUM; i++) {
            // 이미 해당 숫자를 선택한 경우에는 스킵.
            if (visited[i]) {
                continue;
            }
            // 선택하지 않은 경우, 선택했다는 표시를 해준다.
            visited[i] = true;
            // 숫자를 담는다.
            result[cnt] = target[i];
            // 자신을 재귀 호출한다.
            permutation(cnt + 1);
            // 선택을 해제한다.
            visited[i] = false;
        }
    }

    private static void printAnswer(String answer) {
        if (answer.equals("0")) {
            System.out.println(answer);
            return;
        }
        for (int i = 0; i < answer.length(); i++) {
            System.out.print(answer.charAt(i));
            if ((i+1)%3 == 0) {
                System.out.println();
            }
        }
    }
}
