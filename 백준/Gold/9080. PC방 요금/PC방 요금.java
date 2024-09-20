import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://leemoono.tistory.com/34
public class Main {

    static final int HOUR = 60, DAY = 24,
            HOURLY_FEE = 1000, NIGHT_FEE = 5000,
            NIGHT_START_HOUR = 22, NIGHT_END_HOUR = 8,
            NIGHT_PROFIT_MIN_TIME = 300, NIGHT_PROFIT_END_HOUR = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int TestCaseNum = Integer.parseInt(br.readLine());
        for (int i = 0; i < TestCaseNum; i++) {
            st = new StringTokenizer(br.readLine());

            int[] startTime = parseTime(st.nextToken());
            int usageTime = Integer.parseInt(st.nextToken());

            System.out.println(getTotalFee(startTime[0], startTime[1], usageTime));
        }
    }

    static int getTotalFee(int hour, int minute, int usageTime) {
        int totalFee = 0;
        while (usageTime > 0) {
            if(isNightProfitHour(hour) && usageTime > NIGHT_PROFIT_MIN_TIME) {
                while(hour != NIGHT_END_HOUR) {
                    hour = (hour + 1) % DAY;
                    usageTime -= HOUR;
                }
                totalFee += NIGHT_FEE;
                usageTime += minute;
                minute = 0;
            } else {
                hour = (hour + 1) % DAY;
                usageTime -= HOUR;
                totalFee += HOURLY_FEE;
            }
        }
        return totalFee;
    }

    static boolean isNightProfitHour(int hour) {
        return hour >= NIGHT_START_HOUR || hour < NIGHT_PROFIT_END_HOUR;
    }

    static int[] parseTime(String time) {
        String[] timestamp = time.split(":");
        int hour = Integer.parseInt(timestamp[0]);
        int minute = Integer.parseInt(timestamp[1]);

        return new int[]{hour, minute};
    }
}

