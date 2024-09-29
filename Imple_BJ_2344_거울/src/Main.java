import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point
{
	int x;
	int y;
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}

public class Main {
	
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	
	static int N;
	static int M;
	static int[][] map;
	
	static int w;
	static int a;
	static int b;
	
	public int change(int dir)
	{
		if(dir == 2)
		{
			dir = 3;
		}
		else if(dir == 3)
		{
			dir = 2;
		}
		else if (dir == 1)
		{
			dir = 0;
		}
		else if (dir == 0)
		{
			dir = 1;
		}
		
		return dir;
	}
	
	public void BFS(int x, int y, int dir)
	{
		if(map[x][y] == 1)
		{
			dir = change(dir);
		}
		
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y));
		while(!q.isEmpty())
		{
			Point tmp = q.poll();
			int ni = tmp.x + di[dir];
			int nj = tmp.y + dj[dir];
			if(ni >= 1 && ni <= N && nj >= 1 && nj <= M)
			{
				if(map[ni][nj] == 1)
				{
					dir = change(dir);
				}
				q.add(new Point(ni, nj));
			}
			else
			{
				System.out.print(map[ni][nj] + " ");
//				System.out.println(ni + " " + nj);
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
		
		map = new int[N+2][M+2];
		
		w = 0;
		a = 0;
		b = -1;
		int num = 1;
		for(int i = 1; i < N+1; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j < M+1; j++)
			{
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 1; i <= N; i++)
		{
			map[i][0] = num;
			num++;
		}
		
		for(int j = 1; j < M+1; j++)
		{
			map[N+1][j] = num;
			num++;
		}
		
		for(int i = N; i >= 1; i--)
		{
			map[i][M+1] = num;
			num++;
		}
		
		for(int j = M; j >= 1; j--)
		{
			map[0][j] = num;
			num++;
		}
		
//		for(int i = 0; i < N+2; i++)
//		{
//			System.out.println(Arrays.toString(map[i]));
//		}
		
		for(int i = 1; i < N+1; i++)
		{
			int dir = 1;
			T.BFS(i, 1, dir);
			// start map[i][0];
		}
		
		for(int j = 1; j < M+1; j++)
		{
			int dir = 0;
			T.BFS(N, j, dir);
			// start map[N-1][j];
		}
		
		for(int i = N; i >= 1; i--)
		{
			int dir = 3;
			T.BFS(i, M, dir);
			// start map[i][M-1];
		}
		
		for(int j = M; j >= 1; j--)
		{
			int dir = 2;
			T.BFS(1, j, dir);
			// start map[0][j];
		}
	}
}
