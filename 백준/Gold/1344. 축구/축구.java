import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[] primeNumbers = {2, 3, 5, 7, 11, 13, 17};
    static double[][] binomial = new double[19][19];
    static double goalA, goalB, noGoalA, noGoalB;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int percentGoalA = Integer.parseInt(br.readLine().trim());
        int percentGoalB = Integer.parseInt(br.readLine().trim());

        goalA = percentGoalA / 100.0;
        goalB = percentGoalB / 100.0;
        noGoalA = 1 - goalA;
        noGoalB = 1 - goalB;

        calculateBinomialCoefficients();

        double nonPrimeProb = 0.0;

        for (int scoreA = 0; scoreA <= 18; scoreA++) {
            for (int scoreB = 0; scoreB <= 18; scoreB++) {
                if (!isPrime(scoreA) && !isPrime(scoreB)) {
                    nonPrimeProb += binomial[18][scoreA] * Math.pow(goalA, scoreA) * Math.pow(noGoalA, 18 - scoreA) *
                            binomial[18][scoreB] * Math.pow(goalB, scoreB) * Math.pow(noGoalB, 18 - scoreB);
                }
            }
        }

        double result = 1 - nonPrimeProb;

        System.out.printf("%.9f\n", result);
    }

    private static boolean isPrime(int number) {
        for (int prime : primeNumbers) {
            if (number == prime) {
                return true;
            }
        }
        return false;
    }

    private static void calculateBinomialCoefficients() {
        for (int i = 0; i <= 18; i++) {
            binomial[i][0] = binomial[i][i] = 1;
            for (int j = 1; j < i; j++) {
                binomial[i][j] = binomial[i - 1][j - 1] + binomial[i - 1][j];
            }
        }
    }
}