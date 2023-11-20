import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Solution {
    static enum PersonalType {
        R(0, 1), T(0, 1),
        C(0, 2), F(0, 2),
        J(0, 3), M(0, 3),
        A(0, 4), N(0, 4);

        private int score;
        private final int order;

        PersonalType(int score, int order) {
            this.score = score;
            this.order = order;
        }

        public void addScore(int addNumber) {
            this.score += addNumber;
        }

        public int getScore() {
            return score;
        }

        public int getOrder() {
            return this.order;
        }

        public static String getResult(int order) {
            List<PersonalType> personalTypes = Arrays.stream(PersonalType.values())
                    .filter(type -> type.getOrder() == order)
                    .collect(Collectors.toList());


           return personalTypes.stream()
                    .max(Comparator.comparingInt(PersonalType::getScore)
                            .thenComparing(Comparator.comparing(Enum::name, Comparator.reverseOrder())))
                    .orElse(personalTypes.get(0))
                    .name();
        }
    }

    public static String solution(String[] survey, int[] choices) {

        for (int i = 0; i < survey.length; i++) {
            calculateScore(survey[i], choices[i]);
        }

        String answer = "";

        for (int i = 1; i <= PersonalType.values().length / 2; i++) {
            answer += PersonalType.getResult(i);
        }

        return answer;
    }

    private static void calculateScore(String survey, int choice) {
        String[] personalTypes = survey.split("");

        int score = choice - 4;
        int getScoreType = -1;

        if (score < 0) {
            getScoreType = 0;
        }
        if (score > 0) {
            getScoreType = 1;
        }

        int finalGetScoreType = getScoreType;
        if (finalGetScoreType != -1) {
            Arrays.stream(PersonalType.values())
                    .filter(type -> type.name().equals(personalTypes[finalGetScoreType]))
                    .findFirst()
                    .ifPresent(type -> type.addScore(Math.abs(score)));
        }
    }
}