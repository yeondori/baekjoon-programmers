import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[][] grid;
    static List<Integer>[] trashLocations;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        grid = new int[n][m];
        trashLocations = new ArrayList[n];
        int trashCount = 0;

        for (int i = 0; i < n; i++) {
            trashLocations[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if (grid[i][j] == 1) {
                    trashLocations[i].add(j);
                    trashCount++;
                }
            }
        }

        int robotCount = 0;
        while (trashCount > 0) {
            robotCount++;
            int currentPosition = 0;

            for (int i = 0; i < n; i++) {
                int idx = findNextTrash(trashLocations[i], currentPosition);
                if (idx == -1) continue;

                currentPosition = trashLocations[i].get(trashLocations[i].size() - 1);
                while (!trashLocations[i].isEmpty() && trashLocations[i].get(trashLocations[i].size() - 1) >= idx) {
                    trashLocations[i].remove(trashLocations[i].size() - 1);
                    trashCount--;
                }
            }
        }

        System.out.println(robotCount);
    }

    private static int findNextTrash(List<Integer> row, int position) {
        for (int idx : row) {
            if (idx >= position) {
                return idx;
            }
        }
        return -1;
    }
}
