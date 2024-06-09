import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static class Result {
        int quality;
        String partition;

        Result(int quality, String partition) {
            this.quality = quality;
            this.partition = partition;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String phoneNumber = br.readLine();
        String result = maximizeQuality(phoneNumber);
        System.out.println(result);
    }

    public static String maximizeQuality(String phoneNumber) {
        int n = phoneNumber.length();
        Result[] dp = new Result[n + 1];

        for (int i = 0; i <= n; i++) {
            dp[i] = new Result(-1, "");
        }
        dp[0] = new Result(0, "");

        for (int i = 2; i <= n; i++) {
            if (i >= 2) {
                int quality = calculateQuality(phoneNumber.substring(i - 2, i));
                if (dp[i - 2].quality != -1) {
                    int newQuality = dp[i - 2].quality + quality;
                    String newPartition = dp[i - 2].partition + (dp[i - 2].partition.isEmpty() ? "" : "-") + phoneNumber.substring(i - 2, i);
                    if (newQuality > dp[i].quality || (newQuality == dp[i].quality && newPartition.compareTo(dp[i].partition) < 0)) {
                        dp[i] = new Result(newQuality, newPartition);
                    }
                }
            }

            if (i >= 3) {
                int quality = calculateQuality(phoneNumber.substring(i - 3, i));
                if (dp[i - 3].quality != -1) {
                    int newQuality = dp[i - 3].quality + quality;
                    String newPartition = dp[i - 3].partition + (dp[i - 3].partition.isEmpty() ? "" : "-") + phoneNumber.substring(i - 3, i);
                    if (newQuality > dp[i].quality || (newQuality == dp[i].quality && newPartition.compareTo(dp[i].partition) < 0)) {
                        dp[i] = new Result(newQuality, newPartition);
                    }
                }
            }
        }

        return dp[n].partition;
    }

    private static int calculateQuality(String group) {
        if (group.length() == 2) {
            if (group.charAt(0) == group.charAt(1)) {
                return 2; // '오'
            } else {
                return 0; // '즐'
            }
        } else if (group.length() == 3) {
            if (group.charAt(0) == group.charAt(1) && group.charAt(1) == group.charAt(2)) {
                return 2; // '오'
            } else if (group.charAt(0) == group.charAt(1) || group.charAt(1) == group.charAt(2) || group.charAt(0) == group.charAt(2)) {
                return 1; // '걍'
            } else {
                return 0; // '즐'
            }
        }
        return 0;
    }
}
