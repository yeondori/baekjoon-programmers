import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int machineNum = Integer.parseInt(st.nextToken());
        int round = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer>[] machines = new PriorityQueue[machineNum];
        for (int i = 0; i < machineNum; i++) {
            machines[i] = new PriorityQueue(Collections.reverseOrder());

            st = new StringTokenizer(br.readLine());
            machines[i].add(Integer.parseInt(st.nextToken()));
            machines[i].add(Integer.parseInt(st.nextToken()));
        }

        int winner;
        int loser = 0;

        if (round > machineNum) {
            round = round % machineNum + machineNum;
        }
        for (int i = 0; i < round; i++) {
            for (int j = 0; j < machineNum; j++) {
                if (j == 0) {
                    winner = machines[j].poll();
                    loser = machines[j].poll();
                    machines[j].add(winner);
                } else {
                    machines[j - 1].add(machines[j].poll());

                    if (j == machineNum - 1) {
                        machines[j].add(loser);
                    }
                }
            }
        }

        for (int i = 0; i < machineNum; i++) {
            winner = machines[i].poll();
            loser = machines[i].poll();
            System.out.println(loser + " " + winner);
        }
    }
}