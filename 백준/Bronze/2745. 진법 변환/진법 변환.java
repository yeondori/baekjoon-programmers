import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        ArrayList<Integer> N = toNumList(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int ans = toConvert(N,B);

        if(ans<=1_000_000_000) System.out.println(ans);

    }

    static ArrayList<Integer> toNumList(String s) {

        ArrayList<Integer> num = new ArrayList<Integer>();
        for (int i=0; i<s.length(); i++){

            if (s.charAt(i)>=65&&s.charAt(i)<=90) {
                num.add(s.charAt(i) - 55);
            } else num.add(s.charAt(i)-48);
        }
        return num;
    }

    static int toConvert (ArrayList<Integer> num, int B) {

        int ans = 0;

        Collections.reverse(num);
        for(int i=0;i<num.size();i++)
        {
            ans+=Math.pow(B,i)*num.get(i);
        }
        return ans;
    }
}