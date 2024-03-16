import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static int N, K;
	static Deque<Integer> peoples;
	static StringBuilder answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		answer = new StringBuilder("<");
		peoples = new ArrayDeque<Integer>();
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= N; i++) {
			peoples.add(i);
		}
		
		getAnswer();
		System.out.println(answer);
	}

	private static void getAnswer() {
		while(peoples.size()>1) {
			for (int i = 0; i < K-1; i++) {
				peoples.offer(peoples.poll());
			}
			answer.append(peoples.poll()).append(", ");
		}
		answer.append(peoples.poll()).append(">");
	}
}
