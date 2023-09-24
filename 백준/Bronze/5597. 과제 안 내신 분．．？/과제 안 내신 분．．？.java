import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        final int studentNum = 30;
        final int numSummitStudent = 28;

        int[] stu = new int[studentNum];
        Arrays.setAll(stu, num -> num + 1);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i= 0; i<numSummitStudent; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int summitStu = Integer.parseInt(st.nextToken());

            stu[summitStu-1] = 0;
        }

        for (int j= 0; j<stu.length; j++) {
            if (stu[j] != 0)
                System.out.println(stu[j]);
        }

    }
}