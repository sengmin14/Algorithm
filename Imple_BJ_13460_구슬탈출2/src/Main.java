import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
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
	static char[][] map;
	static char[][] copyMap;
	static int[] arr;
	static int solution = 100;
	
	boolean A = true;
	boolean B = false;
	
	public void copy()
	{
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < M; j++)
			{
				map[i][j] = copyMap[i][j];
			}
		}
	}
	
	public void BFS(int x, int y, char color, int dir, int idx)
	{
		Queue<Point> q = new ArrayDeque<>();
		q.add(new Point(x, y));
		while(!q.isEmpty())
		{
			Point tmp = q.poll();
			int ni = tmp.x + di[dir];
			int nj = tmp.y + dj[dir];
			if(ni >= 0 && ni < N && nj >= 0 && nj < M)
			{
				if(map[ni][nj] == '.')
				{
					map[tmp.x][tmp.y] = '.';
					map[ni][nj] = color;
					q.add(new Point(ni, nj));
				}
				else if(map[ni][nj] == 'O')
				{
					map[tmp.x][tmp.y] = '.';
					if(color == 'B')
					{
						B = true;
					}
					else
					{
						A = true;
					}
				}
			}
			
		}
	}
	
	public void find(int dir, int idx)
	{
		if(dir == 0)
		{
			for(int i = 0; i < N; i++)
			{
				for(int j = 0; j < M; j++)
				{
					if(map[i][j] == 'R')
					{
						BFS(i, j, 'R', dir, idx);
					}
					if(map[i][j] == 'B')
					{
						BFS(i, j, 'B', dir, idx);
					}
				}
			}
		}
		else if(dir == 1)
		{
			for(int i = 0; i < N; i++)
			{
				for(int j = M-1; j >= 0; j--)
				{
					if(map[i][j] == 'R')
					{
						BFS(i, j, 'R', dir, idx);
					}
					if(map[i][j] == 'B')
					{
						BFS(i, j, 'B', dir, idx);
					}
				}
			}
		}
		else if(dir == 2)
		{
			for(int i = N-1; i >= 0; i--)
			{
				for(int j = 0; j < M; j++)
				{
					if(map[i][j] == 'R')
					{
						BFS(i, j, 'R', dir, idx);
					}
					if(map[i][j] == 'B')
					{
						BFS(i, j, 'B', dir, idx);
					}
				}
			}
		}
		else if(dir == 3)
		{
			for(int i = 0; i < N; i++)
			{
				for(int j = 0; j < M; j++)
				{
					if(map[i][j] == 'R')
					{
						BFS(i, j, 'R', dir, idx);
					}
					if(map[i][j] == 'B')
					{
						BFS(i, j, 'B', dir, idx);
					}
				}
			}
		}
		if(A == true && B == false)
		{
			solution = Integer.min(solution, idx);
		}
	}
	
	public void comb(int depth)
	{
		if(depth == 10)
		{
			A = false;
			B = false;
			copy();
			for(int i = 0; i < 10; i++)
			{
//				for(int k = 0; k < 10; k++)
//				{
//					arr[k] = 1;
//				}
				find(arr[i], i+1);
			}
		}
		else
		{
			for(int i = 0; i < 4; i++)
			{
				arr[depth] = i;
				if(depth >= 1)
				{
					if(arr[depth] != arr[depth-1])
					{
						arr[depth] = i;
						comb(depth+1);
					}
				}
				else
				{
					comb(depth+1);
				}
//				arr[depth] = i;
//				comb(depth+1);
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
		map = new char[N][M];
		copyMap = new char[N][M];
		arr = new int[10];
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			for(int j = 0; j < M; j++)
			{
				map[i][j] = str.charAt(j);
				copyMap[i][j] = map[i][j];
			}
		}
		
		T.comb(0);
		if(solution > 10)
		{
			System.out.println(-1);
		}
		else
		{
			System.out.println(solution);
		}
//		System.out.println(solution);
	}
}
