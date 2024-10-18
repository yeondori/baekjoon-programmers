import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Integer> buildingHeights;
    static int totalBuildings, visibleFromKahi, visibleFromDanbi;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        totalBuildings = scanner.nextInt();
        visibleFromKahi = scanner.nextInt();
        visibleFromDanbi = scanner.nextInt();

        buildingHeights = new ArrayList<>();

        if (visibleFromKahi + visibleFromDanbi > totalBuildings + 1) {
            System.out.print(-1);
            return;
        }

        for (int i = 1; i < visibleFromKahi; i++) {
            buildingHeights.add(i);
        }
        buildingHeights.add(Math.max(visibleFromKahi, visibleFromDanbi));

        for (int i = visibleFromDanbi - 1; i >= 1; i--) {
            buildingHeights.add(i);
        }

        if (visibleFromKahi == 1) {
            while (buildingHeights.size() < totalBuildings) {
                buildingHeights.add(1, 1);
            }
        } else {
            while (buildingHeights.size() < totalBuildings) {
                buildingHeights.add(0, 1);
            }
        }
        
        for (int height : buildingHeights) {
            System.out.print(height + " ");
        }
    }
}
