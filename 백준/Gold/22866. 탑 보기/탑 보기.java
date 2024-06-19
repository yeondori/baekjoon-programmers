import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Building {
        int height;
        int index;

        public Building(int height, int index) {
            this.height = height;
            this.index = index;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] heights = new int[N];
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        int[] visibleCount = new int[N];
        int[] closestVisible = new int[N];
        Arrays.fill(closestVisible, -1);

        Stack<Building> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && stack.peek().height <= heights[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                visibleCount[i] += stack.size();
                closestVisible[i] = stack.peek().index + 1; 
            }
            stack.push(new Building(heights[i], i));
        }

        stack.clear();
        for (int i = N - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek().height <= heights[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                visibleCount[i] += stack.size();
                if (closestVisible[i] == -1 || Math.abs(stack.peek().index - i) < Math.abs(closestVisible[i] - 1 - i)) {
                    closestVisible[i] = stack.peek().index + 1; 
                }
            }
            stack.push(new Building(heights[i], i));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(visibleCount[i]);
            if (visibleCount[i] > 0) {
                sb.append(" ").append(closestVisible[i]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
