import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	final static int INF = Integer.MAX_VALUE >> 2;
	static int T, N;
	static int[][] coordinates;
	static int[][] cost;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <=T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			coordinates = new int[N+2][2];
			for (int i = 0; i < N+2; i++) {
				String[] pos = br.readLine().split(" ");
				coordinates[i][0] = Integer.parseInt(pos[0]);
				coordinates[i][1] = Integer.parseInt(pos[1]);
			}
			
			getCost();
			System.out.println(cost[0][N+1]==INF?"sad":"happy");
		}
	}

	private static void getCost() {
		init();
		
		for (int k = 0; k < N+2; k++) {
			for (int i = 0; i < N+2; i++) {
				for (int j = 0; j < N+2; j++) {
					if (cost[i][j] > cost[i][k] + cost[k][j]) {
						cost[i][j] = cost[i][k] + cost[k][j];
					}
				}
			}
		}
	}

	private static void init() {
		cost = new int[N+2][N+2];
		
		for (int i = 0; i < N+2; i++) {
			for (int j = 1; j < N+2; j++) {
				int diffX = Math.abs(coordinates[i][0] - coordinates[j][0]);
				int diffY = Math.abs(coordinates[i][1] - coordinates[j][1]);
				int num = diffX + diffY; 
				
				if (num > 1000) {
					num = INF;
				}
				
				cost[i][j] =  num;
				cost[j][i] =  num;
			}
		}
	}
}
