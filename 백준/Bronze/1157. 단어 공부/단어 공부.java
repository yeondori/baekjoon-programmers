import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().toUpperCase().split("");
        String[] distInput = Arrays.stream(input).distinct().toArray(String[]::new);
        int[] freq = new int[distInput.length];
        int maxCount = 0;
        int idx = -1;

        for (int i=0; i<distInput.length;i++) {
            int count = Collections.frequency(Arrays.asList(input), distInput[i]);
            freq[i] = count;
            if (count > maxCount) {
                maxCount = count;
                idx = i;
            }
        }

        Arrays.sort(freq);
        System.out.println(freq.length>=2? (freq[freq.length-1] == freq[freq.length-2]? "?" : distInput[idx]): distInput[idx]);
    }
}