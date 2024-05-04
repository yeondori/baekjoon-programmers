import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int Size;
    static boolean[] lights, lightsWhenFirstPush, target;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Size = Integer.parseInt(br.readLine());

        lights = new boolean[Size];
        lightsWhenFirstPush = new boolean[Size];  // 첫번째 버튼을 누른 경우의 전구 상태
        String input = br.readLine();
        for (int i = 0; i < Size; i++) {
            lights[i] = input.charAt(i) == '1';
            lightsWhenFirstPush[i] = lights[i];
        }

        lightsWhenFirstPush[0] = !lightsWhenFirstPush[0];
        lightsWhenFirstPush[1] = !lightsWhenFirstPush[1];

        target = new boolean[Size];
        input = br.readLine();
        for (int i = 0; i < Size; i++) {
            target[i] = input.charAt(i) == '1';
        }
        solve();
    }

    private static void solve() {
        int answerWhenNoPush = getMinCount(lights, target);
        int answerWhenFirstPush = getMinCount(lightsWhenFirstPush, target);

        if(answerWhenFirstPush != -1) answerWhenFirstPush++;

        if (answerWhenNoPush == -1) {
            System.out.println(answerWhenFirstPush);
        } else if (answerWhenFirstPush == -1) {
            System.out.println(answerWhenNoPush);
        } else {
            System.out.println(Math.min(answerWhenFirstPush + 1, answerWhenNoPush));
        }
    }

    private static int getMinCount(boolean[] lights, boolean[] target) {
        int cnt = 0;
        for (int i = 0; i < Size - 1; i++) {
            if (lights[i] != target[i]) {
                cnt++;
                lights[i] = !lights[i];
                lights[i + 1] = !lights[i + 1];

                if (i != Size - 2) {
                    lights[i + 2] = !lights[i + 2];
                }
            }
        }
        return lights[Size - 1] != target[Size - 1] ? -1 : cnt;
    }
}