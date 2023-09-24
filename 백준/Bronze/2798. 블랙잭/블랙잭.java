import java.io.*;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer NM = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(NM.nextToken());
        int M = Integer.parseInt(NM.nextToken());

        int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int ans = 0;
        for(int i= 0; i<N-2; i++) {
            for(int j=i+1; j<N-1; j++) {
                for(int k=j+1; k<N; k++){
                    int result = arr[i] + arr[j] + arr[k];
                    if (result <= M && ans<result) {
                        ans = result;
                    }
                }
            }
        }
        System.out.println(ans);
    }
}