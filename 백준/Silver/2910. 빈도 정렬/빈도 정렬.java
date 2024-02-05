import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	
	static int N,C, maxFreq;
	static Map<Integer, Integer> frequency;
	static List<Integer> orders;
	static StringBuilder answer;
	
	static void addAnswer(int num, int repeatNum) {
		for (int i = 0; i<repeatNum; i++) {
			answer.append(num).append(" ");
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		answer = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		frequency = new HashMap<>();
		orders = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		int freq;
		for (int i=1; i<=N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (frequency.get(num) == null) {
				freq  = 1;
			} else {
				freq = frequency.get(num) + 1;
			}
			maxFreq = Math.max(maxFreq, freq);
			frequency.put(num, freq);
			
			if (!orders.contains(num)) {
				orders.add(num);
			}
		}
		
		while(maxFreq>0) {
			for (int i = 0, size = orders.size(); i<size; i++) {
				if (frequency.get(orders.get(i))==maxFreq) {
					addAnswer(orders.get(i), maxFreq);
				}
			}
			maxFreq--;
		}
		
		System.out.println(answer);
	}
}
