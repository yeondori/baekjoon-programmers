import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, cityDistance, answer;
	static int[] combChicken;
	static ArrayList<int[]> chickens, homes;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 입력 받기, 초기화
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		combChicken = new int[M];
		chickens = new ArrayList<>();
		homes = new ArrayList<>();
		answer = Integer.MAX_VALUE;
		
		int cur;
		for (int i = 0; i<N; i++) {
			st  = new StringTokenizer(br.readLine());
			for (int j = 0; j<N; j++) {
				cur = Integer.parseInt(st.nextToken());			
				if (cur == 1) {
					homes.add(new int[] {i, j});
					continue;
				} 
				if (cur == 2) {
					chickens.add(new int[] {i, j});
				}
			}
		}

		combination(0, 0);
		System.out.println(answer);
	}

	
	private static void combination(int idx, int start) {
		if (idx == M) {
			getCityDistance();
			answer = Math.min(answer, cityDistance);
			return;
		}
		for (int i = start; i < chickens.size(); i++) {
			// 탐색할 치킨집의 인덱스 조합 뽑기
			combChicken[idx] = i;
			combination(idx + 1, i + 1);
		}
	}


	// 도시 거리 구하기
	private static void getCityDistance() {
		cityDistance = 0;
		for (int i = 0, size = homes.size(); i<size; i++) {
			int[] home = homes.get(i); 
			cityDistance += getChickenDistnace(home[0], home[1]);
		}
	}

	// 치킨 거리 구하기
	private static int getChickenDistnace(int x, int y) {
		int chickenDistnace = Integer.MAX_VALUE;
		
		for (int i = 0; i<M; i++) {
			int[] chick = chickens.get(combChicken[i]);
			int dist = Math.abs(chick[0]- x) + Math.abs(chick[1] - y);
			chickenDistnace = Math.min(chickenDistnace, dist);
		}
		return chickenDistnace;
	}
}
