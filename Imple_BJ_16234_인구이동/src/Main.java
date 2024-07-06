import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point
{
	int x;
	int y;
	Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}

class Main
{
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	
	static int N;
	static int L; // 차이 최소 이상
	static int R; // 차이 최대 이하
	
	static int[][] map;
	static int[][] Nmap;
	
	static boolean[][] visited;
	
	static boolean flag;
	
	static int answer;
	
	static int[] arr;
	
	public void countZ()
	{
		int count = 0;
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				if(Nmap[i][j] == 0)
				{
					count++;
				}
			}
		}
		if(count == N*N)
		{
			flag = true;;
		}
	}
	
	public void calc(int cnt)
	{
		int[] count = new int[cnt+1];
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				if(Nmap[i][j] != 0)
				{
					arr[Nmap[i][j]] += map[i][j];
					count[Nmap[i][j]]++;
				}
			}
		}
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				if(Nmap[i][j] != 0)
				{
					map[i][j] = arr[Nmap[i][j]]/count[Nmap[i][j]];
				}
			}
		}
		
	}
	
	
	public void BFS(int x, int y, int cnt)
	{
		visited[x][y] = true;
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(x, y));
		// System.out.println(x + " " + y);
		while(!q.isEmpty())
		{
			Point tmp = q.poll();
			// System.out.println(tmp.x + " " + tmp.y);
			for(int i = 0; i < 4; i++)
			{
				int ni = tmp.x + di[i];
				int nj = tmp.y + dj[i];
				// System.out.println(tmp.x + " " + tmp.y);
				if(ni >= 0 && ni < N && nj >= 0 && nj < N && visited[ni][nj] == false)
				{
					// System.out.println(tmp.x + " " + tmp.y);
					// System.out.println(ni + " " + nj);
					// System.out.println(map[ni][nj] + " " + map[tmp.x][tmp.y]);
					if(Math.abs(map[ni][nj] - map[tmp.x][tmp.y]) >= L && Math.abs(map[ni][nj] - map[tmp.x][tmp.y]) <= R )
					{
						Nmap[x][y] = cnt;
						Nmap[ni][nj] = cnt;
						q.offer(new Point(ni, nj));
						visited[ni][nj] = true;
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
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++)
			{
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(flag == false)
		{
			
			Nmap = new int[N][N];
			visited = new boolean[N][N];
			
			int cnt = 1;
			for(int i = 0; i < N; i++)
			{
				for(int j = 0; j < N; j++)
				{
					if(visited[i][j] == false)
					{
						T.BFS(i, j, cnt);
						cnt++;
					}
				}
			}
			arr = new int[cnt+1];
			T.countZ();
			T.calc(cnt);
			answer++;
			
		}
		
		
		System.out.println(answer-1);
//		for(int i = 0; i < N; i++)
//		{
//			System.out.println(Arrays.toString(Nmap[i]));
//		}
	}
}