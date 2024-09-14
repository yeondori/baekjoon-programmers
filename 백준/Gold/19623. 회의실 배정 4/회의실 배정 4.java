import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] maxVolume = new int[100001];

    static class Meeting implements Comparable<Meeting> {
        int start, end, volume;

        public Meeting(int start, int end, int volume) {
            this.start = start;
            this.end = end;
            this.volume = volume;
        }

        @Override
        public int compareTo(Meeting o) {
            return this.start - o.start;
        }
    }

    static Meeting[] meetings;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        meetings = new Meeting[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int volume = Integer.parseInt(st.nextToken());

            meetings[i] = new Meeting(start, end, volume);
        }

        solve();
    }

    static void solve() {
        Arrays.sort(meetings);
        Arrays.fill(maxVolume, -1);

        System.out.println(dp(0));
    }

    static int searchNextStartTime(int curEndTime) {
        int str = 0;
        int end = N;
        int mid;
        while( str < end) {
            mid = (str + end) / 2;

            if (meetings[mid].start >= curEndTime) {
                end = mid;
            } else {
                str = mid + 1;
            }
        }
        return str;
    }

    static int dp(int idx) {
        if (idx >= N) return 0;

        if (maxVolume[idx] != -1) return maxVolume[idx];

        return maxVolume[idx] = Math.max(dp(idx+1), meetings[idx].volume + dp(searchNextStartTime(meetings[idx].end)));
    }
}
