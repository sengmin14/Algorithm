import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point
{
	int x;
	int y;
	int cnt;
	int wall;
	public Point(int x, int y, int cnt, int wall)
	{
		this.x = x;
		this.y = y;
		this.cnt = cnt;
		this.wall = wall;
	}
}

public class Main {
	
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	
	static int N;
	static int M;
	static int[][] map;
	static boolean[][][] visited;
	
	static Point point;
	
	static Point end;
	
	static int answer = Integer.MAX_VALUE;
	
	public void BFS()
	{
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(point.x, point.y, 0, 0));
		visited[0][point.x][point.y] = true;
		
		while(!q.isEmpty())
		{
			Point tmp = q.poll();
			if(tmp.x == end.x && tmp.y == end.y)
			{
				answer = tmp.cnt;
				return;
				// answer = Integer.min(answer, tmp.cnt);
			}
			for(int d = 0; d < 4; d++)
			{
				int ni = tmp.x + di[d];
				int nj = tmp.y + dj[d];
				if(ni >= 0 && ni < N && nj >= 0 && nj < M)
				{
					// if(visited[tmp.wall][ni][nj] == false)
					{
						if(map[ni][nj] == 0 && visited[tmp.wall][ni][nj] == false)
						{
							visited[tmp.wall][ni][nj] = true;
							q.add(new Point(ni, nj, tmp.cnt + 1, tmp.wall));
						}
						else if(map[ni][nj] == 1)
						{
							if(tmp.wall == 0)
							{
								visited[1][ni][nj] = true;
								q.add(new Point(ni, nj, tmp.cnt + 1, 1));
							}
						}
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		Main T = new Main();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[2][N][M];
		
		// start Point
		st = new StringTokenizer(br.readLine());
		int Hx = Integer.parseInt(st.nextToken())-1;
		int Hy = Integer.parseInt(st.nextToken())-1;
		
		point = new Point(Hx, Hy, 0, 0);
		
		// end Point
		st = new StringTokenizer(br.readLine());
		int Ex = Integer.parseInt(st.nextToken())-1;
		int Ey = Integer.parseInt(st.nextToken())-1;
		
		end = new Point(Ex, Ey, 0, 0);
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++)
			{
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		T.BFS();
		if(answer == Integer.MAX_VALUE)
		{
			answer = -1;
		}
		System.out.println(answer);
		
	}
}
