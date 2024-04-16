import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int TotalNum, AddNum, MultiNum, maxNum;
    static int[] input, numbers;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        TotalNum = Integer.parseInt(st.nextToken());
        input = new int[TotalNum];
        numbers = new int[TotalNum];
        check = new boolean[TotalNum];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < TotalNum; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        AddNum = Integer.parseInt(st.nextToken());
        MultiNum = Integer.parseInt(st.nextToken());
        maxNum = Integer.MIN_VALUE;

        back(0);
        System.out.println(maxNum);
    }

    public static void back(int depth) {
        if (depth == TotalNum) {
            boolean[] oper = new boolean[TotalNum - 1];
            back2(0, 0, numbers, oper);
            return;
        }

        for (int i = 0; i < TotalNum; i++) {
            if (!check[i]) {
                check[i] = true;
                numbers[depth] = input[i];
                back(depth + 1);
                check[i] = false;
            }
        }
    }

    public static void back2(int a, int depth, int[] nums, boolean[] oper) {
        if (depth == AddNum) {
            int[] sum = new int[MultiNum + 1];
            int idx = 0;
            for (int i = 0; i < oper.length; i++) {
                if (i == 0 && oper[i]) {
                    sum[idx] += nums[i] + nums[i + 1];
                } else if (oper[i] && oper[i - 1]) {
                    sum[idx] += nums[i + 1];
                } else if (!oper[i]) {
                    idx++;
                    sum[idx] += nums[i + 1];
                } else if (oper[i] && !oper[i - 1]) {
                    sum[idx] += nums[i + 1];
                }
            }
            if (!oper[0]) sum[0] = nums[0];
            int res = 1;
            for (int s : sum) res *= s;
            maxNum = Math.max(res, maxNum);
        }

        for (int i = a; i < oper.length; i++) {
            if (oper[i]) continue;
            oper[i] = true;
            back2(i, depth + 1, nums, oper);
            oper[i] = false;
        }
    }
}
