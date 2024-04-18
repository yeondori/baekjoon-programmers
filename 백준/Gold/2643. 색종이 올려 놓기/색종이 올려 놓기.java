import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    
    static int paperNum;
    static class Paper implements Comparable<Paper> {
        int max, min;

        public Paper(int max, int min) {
            this.max = max;
            this.min = min;
        }

        @Override
        public int compareTo(Paper o) {
            if (this.max == o.max)
                return Integer.compare(this.min, o.min);
            return Integer.compare(this.max, o.max);
        }
    }

    static Paper[] inputPapers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        paperNum = Integer.parseInt(br.readLine());
        inputPapers = new Paper[paperNum];
        for (int i = 0; i < paperNum; i++) {
            String[] input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);

            int max = Math.max(x, y);
            int min = Math.min(x, y);

            inputPapers[i] = new Paper(max, min);
        }

        Arrays.sort(inputPapers);
        System.out.println(getLis());
    }

    private static int getLis() {
        int[] lis = new int[paperNum];
        Arrays.fill(lis, 1);

        for (int i = 1; i < paperNum; i++) {
            for (int j = 0; j < i; j++) {
                if (inputPapers[j].min <= inputPapers[i].min) {
                    lis[i] = Math.max(lis[i], lis[j] + 1);
                }
            }
        }

        int maxLis = 0;
        for (int i = 0; i < paperNum; i++) {
            maxLis = Math.max(maxLis, lis[i]);
        }
        
        return maxLis;
    }
}
