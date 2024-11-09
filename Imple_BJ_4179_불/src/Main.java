import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Fire
{
	int x;
	int y;
	int cnt;
	public Fire(int x, int y, int cnt)
	{
		this.x = x;
		this.y = y;
		this.cnt  = cnt;
	}
}

class Point
{
	int x;
	int y;
	int cnt;
	public Point(int x, int y, int cnt)
	{
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
}

public class Main {

	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	
	static int N;
	static int M;
	static int[][] map;
	static int[][] fireMap;
	
	static boolean[][] visited;
	static boolean[][] fireVisited;
	
	static Queue<Fire> fire = new LinkedList<>();
	
	
	public void fire()
	{
		fireVisited = new boolean[N][M];
		while(!fire.isEmpty())
		{
			Fire tmp = fire.poll();
			fireMap[tmp.x][tmp.y] = tmp.cnt;
			fireVisited[tmp.x][tmp.y] = true;
			for(int d = 0; d < 4; d++)
			{
				int ni = tmp.x + di[d];
				int nj = tmp.y + dj[d];
				if(ni >= 0 && ni < N && nj >= 0 && nj < M)
				{
					if(map[ni][nj] != -1 && fireVisited[ni][nj] == false)
					{
						fireMap[ni][nj] = tmp.cnt + 1;
						fire.add(new Fire(ni, nj, tmp.cnt + 1));
						fireVisited[ni][nj] = true;
					}
				}
			}
		}
		
	}
	
	public int BFS(int x, int y)
	{
		Queue<Point> q = new LinkedList<>();
		visited = new boolean[N][M];
		q.add(new Point(x, y, 0));
		visited[x][y] = true;
		if(x == 0 || x == N-1 || y == 0 || y == M-1)
		{
			return 1;
		}
		while(!q.isEmpty())
		{
			Point tmp = q.poll();
			for(int d = 0; d < 4; d++)
			{
				int ni = tmp.x + di[d];
				int nj = tmp.y + dj[d];
				if(ni >= 0 && ni < N && nj >= 0 && nj < M && visited[ni][nj] == false)
				{
					if(map[ni][nj] == 0 && fireMap[ni][nj] > tmp.cnt+1)
					{
						visited[ni][nj] = true;
						map[ni][nj] = tmp.cnt + 1;
						q.offer(new Point(ni, nj, tmp.cnt + 1));
						if(ni == 0 || ni == N-1 || nj == 0 || nj == M-1)
						{
							return tmp.cnt + 2;
						}
					}
				}
				
			}
		}
		return Integer.MAX_VALUE;
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
		fireMap = new int[N][M];
		int startX = 0;
		int startY = 0;
		int fireX = 0;
		int fireY = 0;
		
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < M; j++)
			{
				fireMap[i][j] = Integer.MAX_VALUE;
			}
		}
		
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			for(int j = 0; j < M; j++)
			{
				if(str.charAt(j) == '#')
				{
					map[i][j] = -1;
				}
				else if(str.charAt(j) == '.')
				{
					map[i][j] = 0;
				}
				else if(str.charAt(j) == 'F')
				{
					fire.add(new Fire(i, j, 0));
				}
				else
				{
					startX = i;
					startY = j;
				}
			}
		}
		
		T.fire();
		
		int answer = T.BFS(startX, startY);
		if(answer == Integer.MAX_VALUE)
		{
			System.out.println("IMPOSSIBLE");
		}
		else
		{
			System.out.println(answer);
		}
		
//		System.out.println(T.BFS(startX, startY));
//		
//		
//		for(int i = 0; i < N; i++)
//		{
//			System.out.println(Arrays.toString(map[i]));
//		}
//		
//		for(int i = 0; i < N; i++)
//		{
//			System.out.println(Arrays.toString(fireMap[i]));
//		}
		
	}
}
