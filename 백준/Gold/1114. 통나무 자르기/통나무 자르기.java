import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        long L = Long.parseLong(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        List<Long> positions = new ArrayList<>();
        positions.add(0L);   
        positions.add(L);    

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            positions.add(Long.parseLong(st.nextToken()));
        }
        Collections.sort(positions);

        long left = 1;
        long right = L;
        long ansFirstCut = 0;
        long ansLongestPiece = L;

        while (left <= right) {
            long mid = (left + right) / 2;
            long cutCount = 0;
            long firstCut = -1;
            long segmentLength = 0;
            boolean canCut = true;

            for (int i = K; i >= 0; i--) {
                segmentLength += positions.get(i + 1) - positions.get(i);
                
                if (segmentLength > mid) {
                    segmentLength = positions.get(i + 1) - positions.get(i);
                    cutCount++;
                    if (cutCount > C || segmentLength > mid) {
                        canCut = false;
                        break;
                    }
                }
            }

            if (canCut) {
                if (cutCount < C) {
                    firstCut = positions.get(1);
                } else {
                    firstCut = positions.get(positions.indexOf(segmentLength));
                }

                if (mid < ansLongestPiece || (mid == ansLongestPiece && firstCut < ansFirstCut)) {
                    ansLongestPiece = mid;
                    ansFirstCut = firstCut;
                }
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(ansLongestPiece + " " + ansFirstCut);
    }
}
