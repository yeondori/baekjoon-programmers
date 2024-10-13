import java.util.*;

class Solution {
    private static final int SIZE = 50;
    private String[][] table = new String[SIZE + 1][SIZE + 1];
    private int[][] parent = new int[SIZE * SIZE + 1][2];
    private List<String> result = new ArrayList<>();

    public String[] solution(String[] commands) {
        for (int i = 1; i <= SIZE; i++) {
            for (int j = 1; j <= SIZE; j++) {
                int id = encode(i, j);
                parent[id][0] = i;
                parent[id][1] = j;
            }
        }

        for (String command : commands) {
            String[] parts = command.split(" ");
            switch (parts[0]) {
                case "UPDATE":
                    if (parts.length == 4) {
                        int r = Integer.parseInt(parts[1]);
                        int c = Integer.parseInt(parts[2]);
                        String value = parts[3];
                        updateCell(r, c, value);
                    } else if (parts.length == 3) {
                        String value1 = parts[1];
                        String value2 = parts[2];
                        updateAll(value1, value2);
                    }
                    break;
                case "MERGE":
                    int r1 = Integer.parseInt(parts[1]);
                    int c1 = Integer.parseInt(parts[2]);
                    int r2 = Integer.parseInt(parts[3]);
                    int c2 = Integer.parseInt(parts[4]);
                    mergeCells(r1, c1, r2, c2);
                    break;
                case "UNMERGE":
                    int r = Integer.parseInt(parts[1]);
                    int c = Integer.parseInt(parts[2]);
                    unmergeCell(r, c);
                    break;
                case "PRINT":
                    int pr = Integer.parseInt(parts[1]);
                    int pc = Integer.parseInt(parts[2]);
                    printCell(pr, pc);
                    break;
            }
        }
        return result.toArray(new String[0]);
    }

    private int encode(int r, int c) {
        return (r - 1) * SIZE + c;
    }

    private int[] find(int r, int c) {
        int id = encode(r, c);
        if (parent[id][0] == r && parent[id][1] == c) {
            return new int[]{r, c};
        }
        return parent[id] = find(parent[id][0], parent[id][1]);
    }

    private void updateCell(int r, int c, String value) {
        int[] root = find(r, c);
        table[root[0]][root[1]] = value;
    }

    private void updateAll(String value1, String value2) {
        for (int i = 1; i <= SIZE; i++) {
            for (int j = 1; j <= SIZE; j++) {
                int[] root = find(i, j);
                if (value1.equals(table[root[0]][root[1]])) {
                    table[root[0]][root[1]] = value2;
                }
            }
        }
    }

    private void mergeCells(int r1, int c1, int r2, int c2) {
        int[] root1 = find(r1, c1);
        int[] root2 = find(r2, c2);

        if (Arrays.equals(root1, root2)) return;

        String value1 = table[root1[0]][root1[1]];
        String value2 = table[root2[0]][root2[1]];

        if (value1 == null && value2 != null) {
            parent[encode(root1[0], root1[1])] = root2;
        } else {
            parent[encode(root2[0], root2[1])] = root1;
        }
    }

    private void unmergeCell(int r, int c) {
        int[] root = find(r, c);
        String value = table[root[0]][root[1]];

        List<int[]> mergedCells = new ArrayList<>();
        for (int i = 1; i <= SIZE; i++) {
            for (int j = 1; j <= SIZE; j++) {
                int[] currentRoot = find(i, j);
                if (Arrays.equals(currentRoot, root)) {
                    mergedCells.add(new int[]{i, j});
                }
            }
        }

        for (int[] cell : mergedCells) {
            parent[encode(cell[0], cell[1])] = new int[]{cell[0], cell[1]};
            table[cell[0]][cell[1]] = null;
        }

        table[r][c] = value;
    }

    private void printCell(int r, int c) {
        int[] root = find(r, c);
        String value = table[root[0]][root[1]];
        if (value == null) {
            result.add("EMPTY");
        } else {
            result.add(value);
        }
    }
}
