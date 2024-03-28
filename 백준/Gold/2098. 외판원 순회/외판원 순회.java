import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	final static int INF = 98765432;
	static int N;
	static int[][] cost;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cost = new int[N][N];
		dp = new int[N][1<<N];	
		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				cost[i][j] = Integer.parseInt(input[j]);
			}
			Arrays.fill(dp[i], -1);
		}
		int answer = travel(0, 1);
		System.out.println(answer);
	}
	
	private static int travel(int cur, int visit) {
		if (visit == (1<<N)-1) {
			return cost[cur][0]!=0? cost[cur][0] : INF; 
		}
		
		if (dp[cur][visit] != -1) {
			return dp[cur][visit];
		}
		int temp = INF;
		
		for (int i = 1; i < N; i++) {
			if (cost[cur][i]==0) continue;
			if ((visit & (1<<i)) != 0) continue; 
			
			temp = Math.min(temp, travel(i, visit|(1<<i)) + cost[cur][i]);
		}
		return dp[cur][visit] = temp;
	}

}
