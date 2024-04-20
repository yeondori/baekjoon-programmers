import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int sensorNum = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        List<Integer> sensors = new ArrayList<>();
        for (int i = 0; i < sensorNum; i++) {
            sensors.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(sensors);

        List<Integer> distances = new ArrayList<>();
        for (int i = 1; i < sensorNum; i++) {
            distances.add(sensors.get(i) - sensors.get(i - 1));
        }

        Collections.sort(distances);


        for (int i = 0; i < Math.min(k - 1, sensorNum - 1); i++) {
            distances.remove(distances.size() - 1);
        }

        int answer = 0;
        for (int dist : distances) {
            answer += dist;
        }

        System.out.println(answer);
    }
}
