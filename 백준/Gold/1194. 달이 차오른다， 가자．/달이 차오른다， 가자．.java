import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Queue;

public class Main {
	final static int KEY_NUMBER = 6;
	static int N, M, strX, strY; 
	static char[][] maze;
	static boolean[][][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0,0,-1,1};
	static class Pos {
		int x;
		int y;
		int cnt;
		int key;
		
		Pos(int x, int y, int cnt, int key) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.key = key;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maze = new char[N][M];
		visited = new boolean[N][M][1<<KEY_NUMBER+1];
		
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				maze[i][j] = input.charAt(j);
				
				if (maze[i][j]=='0') {
					strX = i;
					strY = j;
				}
			}
		}
		
		System.out.println(bfs(strX, strY, 0));
	}

	private static int bfs(int x, int y, int k) {
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(x,y,0, k));
		visited[x][y][k] = true;
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			for (int i =0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				int key = cur.key;
				
				if (nx<0 || nx ==N || ny<0||ny==M) {
					continue;
				} 
				
				if (visited[nx][ny][key] || maze[nx][ny] == '#') {
					continue;
				}
				
				if (maze[nx][ny]=='1') {
					return cur.cnt+1;
				}
				
				if (maze[nx][ny] >= 65 && maze[nx][ny]<=70) {
					int door = maze[nx][ny] - 64;
					if ((key & (1<<door)) == 0) {
						continue;
					}
				}
				
				if (maze[nx][ny] >= 97 && maze[nx][ny]<=102) {
					int keyNum = maze[nx][ny] - 96;
					key |= (1<<keyNum);
				}
				
				visited[nx][ny][key] = true;
				q.add(new Pos(nx, ny, cur.cnt+1, key));
			}
		}
		return -1;
	}
}
